package spring.jdbc.sql.update.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcSingerDao implements SingerDao {
	private DataSource dataSource;

	private UpdateSinger updateSinger;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.updateSinger = new UpdateSinger(dataSource);
	}

	@Override
	public void update(Singer singer) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		paramMap.put("id", singer.getId());

		updateSinger.updateByNamedParam(paramMap);
	}
}
