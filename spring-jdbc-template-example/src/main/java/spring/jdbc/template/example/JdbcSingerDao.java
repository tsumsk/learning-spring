package spring.jdbc.template.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
