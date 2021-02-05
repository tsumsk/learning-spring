package spring.http.invoker.example.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("jdbc-hikari.properties")
@ComponentScan("spring.http.invoker.example")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "spring.http.invoker.example")
public class DataServiceConfig {
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
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("spring.data.jpa.example.entities");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.afterPropertiesSet();

		return factoryBean.getNativeEntityManagerFactory();
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
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
}
