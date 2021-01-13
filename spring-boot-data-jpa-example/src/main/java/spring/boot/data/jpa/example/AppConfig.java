package spring.boot.data.jpa.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("jdbc-hikari.properties")
public class AppConfig {
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
