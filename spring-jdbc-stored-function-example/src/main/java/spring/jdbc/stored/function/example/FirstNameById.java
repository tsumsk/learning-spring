package spring.jdbc.stored.function.example;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class FirstNameById extends SqlFunction<String> {
	private static final String SQL = "select getFirstNameById(?)";

	public FirstNameById(DataSource dataSource) {
		super(dataSource, SQL);
		declareParameter(new SqlParameter(Types.INTEGER));
		compile();
	}
}
