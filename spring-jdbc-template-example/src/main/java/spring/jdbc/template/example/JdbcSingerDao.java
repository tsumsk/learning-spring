package spring.jdbc.template.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
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

	public String findNameById(Long id) {
		return jdbcTemplate.queryForObject(
			"select concat(first_name, ' ', last_name) from singer where id = ?",
			String.class,
			new Object[] {id}
		);
	}

	public String findNameByIdV2(Long id) {
		Map<String, Object> namedParams = new HashMap<>();
		namedParams.put("id", id);

		return namedParameterJdbcTemplate.queryForObject(
			"select concat(first_name, ' ', last_name) from singer where id = :id",
			namedParams,
			String.class
		);
	}
}
