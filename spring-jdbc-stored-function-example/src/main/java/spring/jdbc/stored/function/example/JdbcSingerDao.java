package spring.jdbc.stored.function.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcSingerDao implements SingerDao {
	private DataSource dataSource;

	private FirstNameById firstNameById;


	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.firstNameById = new FirstNameById(dataSource);
	}

	@Override
	public String firstNameById(Long id) {
		List<String> ret = firstNameById.execute(id);
		return ret.get(0);
	}
}
