package spring.jdbc.mapping.sql.query.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("jdbc-hikari.properties")
@ComponentScan(basePackages = "spring.jdbc.mapping.sql.query.example")
public class Config {
	@Value("${jdbcUrl}")
	private String jdbcUrl;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.addDataSourceProperty("cachePrepStmts", "true");
		dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		dataSource.addDataSourceProperty("useServerPrepStmts", "true");
		dataSource.addDataSourceProperty("useLocalSessionState", "true");
		dataSource.addDataSourceProperty("rewriteBatchedStatements", "true");
		dataSource.addDataSourceProperty("cacheResultSetMetadata", "true");
		dataSource.addDataSourceProperty("cacheServerConfiguration", "true");
		dataSource.addDataSourceProperty("elideSetAutoCommits", "true");
		dataSource.addDataSourceProperty("maintainTimeStats", "true");

		return dataSource;
	}
}
