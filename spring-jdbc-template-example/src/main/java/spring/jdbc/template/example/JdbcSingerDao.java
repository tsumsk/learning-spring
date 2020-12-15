package spring.jdbc.template.example;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class JdbcSingerDao implements SingerDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String findNameById(Long id) {
		Map<String, Object> namedParams = new HashMap<>();
		namedParams.put("id", id);

		return jdbcTemplate.queryForObject(
			"select concat(first_name, ' ', last_name) from singer where id = ?",
			String.class,
			new Object[] {id}
		);
	}
}
