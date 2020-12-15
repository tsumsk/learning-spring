package spring.jdbc.template.example;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao {
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public String findNameById(Long id) {
		return jdbcTemplate.queryForObject(
			"select concat(first_name, ' ', last_name) from singer where id = ?",
			String.class,
			new Object[] {id}
		);
	}

	@Override
	public String findNameByIdV2(Long id) {
		Map<String, Object> namedParams = new HashMap<>();
		namedParams.put("id", id);

		return namedParameterJdbcTemplate.queryForObject(
			"select concat(first_name, ' ', last_name) from singer where id = :id",
			namedParams,
			String.class
		);
	}

	@Override
	public List<Singer> findAll() {
		return namedParameterJdbcTemplate.query(
			"select id, first_name, last_name, birth_date from singer",
			new SingerMapper()
		);
	}

	private static final class SingerMapper implements RowMapper<Singer> {
		@Override
		public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Singer singer = new Singer();
			singer.setId(rs.getLong("id"));
			singer.setFirstName(rs.getString("first_name"));
			singer.setLastName(rs.getString("last_name"));
			singer.setBirthDate(rs.getDate("birth_date"));

			return singer;
		}
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		return namedParameterJdbcTemplate.query(
			"select s.id, s.first_name, s.last_name, s.birth_date, a.id as album_id, a.title, a.release_date " +
				"from singer s left join album a on s.id = a.singer_id",
			new SingerWithDetailExtractor()
		);
	}

	private static final class SingerWithDetailExtractor implements ResultSetExtractor<List<Singer>> {
		@Override
		public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Singer> map = new HashMap<>();

			Singer singer;
			while (rs.next()) {
				Long id = rs.getLong("id");

				singer = map.get(id);
				if (singer == null) {
					singer = new Singer();
					singer.setId(id);
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					singer.setAlbums(new ArrayList<>());
					map.put(id, singer);
				}

				Long albumId = rs.getLong("album_id");
				if (albumId > 0) {
					Album album = new Album();
					album.setId(albumId);
					album.setSingerId(id);
					album.setTitle(rs.getString("title"));
					album.setReleaseDate(rs.getDate("release_date"));
					singer.addAlbum(album);
				}
			}

			return new ArrayList<>(map.values());
		}
	}
}
