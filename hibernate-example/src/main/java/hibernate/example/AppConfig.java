package hibernate.example;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("jdbc-hikari.properties")
@ComponentScan("hibernate.example")
@EnableTransactionManagement
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

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setPackagesToScan("hibernate.example.entities");
		localSessionFactoryBean.setHibernateProperties(hibernateProperties());
		localSessionFactoryBean.afterPropertiesSet();

		return localSessionFactoryBean.getObject();
	}

	private Properties hibernateProperties() {
		Properties  hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.put("hibernate.format_sql", true);
		hibernateProperties.put("hibernate.user_sql_comments", true);
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.max_fetch_depth", 3);
		hibernateProperties.put("hibernate.jdbc.batch_size", 10);
		hibernateProperties.put("hibernate.jdbc.fetch_size", 50);

		return hibernateProperties;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactory());
	}
}
