package spring.jdbc.mapping.sql.query.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSingerDao implements SingerDao {
	private DataSource dataSource;

	private SelectAllSingers selectAllSingers;

	private SelectSingerByFirstName selectSingerByFirstName;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllSingers = new SelectAllSingers(dataSource);
		this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
	}

	@Override
	public String findNameById(Long id) {
		return null;
	}

	@Override
	public List<Singer> findAll() {
		return selectAllSingers.execute();
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);

		return selectSingerByFirstName.executeByNamedParam(paramMap);
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
		return null;
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
