package distributed.transaction.example;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@PropertySource("jdbc.properties")
@ComponentScan("distributed.transaction.example")
@EnableTransactionManagement
public class AppConfig {
	@Value("${databaseName1}")
	private String databaseName1;

	@Value("${username1}")
	private String username1;

	@Value("${password1}")
	private String password1;

	@Value("${databaseName2}")
	private String databaseName2;

	@Value("${username2}")
	private String username2;

	@Value("${password2}")
	private String password2;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource1() {
		AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setUniqueResourceName("XADBMS1");
		dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
		dataSource.setXaProperties(xaProperties1());
		dataSource.setPoolSize(1);

		return dataSource;
	}

	@Bean
	public Properties xaProperties1() {
		Properties properties = new Properties();
		properties.put("databaseName", databaseName1);
		properties.put("user", username1);
		properties.put("password", password1);

		return properties;
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource2() {
		AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
		dataSource.setUniqueResourceName("XADBMS2");
		dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
		dataSource.setXaProperties(xaProperties2());
		dataSource.setPoolSize(1);

		return dataSource;
	}

	@Bean
	public Properties xaProperties2() {
		Properties properties = new Properties();
		properties.put("databaseName", databaseName2);
		properties.put("user", username2);
		properties.put("password", password2);

		return properties;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory1() {
		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource1());
		factoryBean.setPackagesToScan("distributed.transaction.example.entities");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.setPersistenceUnitName("entityManagerFactory1");
		factoryBean.afterPropertiesSet();

		return factoryBean.getNativeEntityManagerFactory();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory2() {
		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource2());
		factoryBean.setPackagesToScan("distributed.transaction.example.entities");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.setPersistenceUnitName("entityManagerFactory2");
		factoryBean.afterPropertiesSet();

		return factoryBean.getNativeEntityManagerFactory();
	}

	private Properties hibernateProperties() {
		Properties  hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.transaction.factory_class",
				"org.hibernate.transaction.JTATransactionFactory");
		hibernateProperties.put("hibernate.transaction.jta.platform",
				"org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform");
		hibernateProperties.put("hibernate.transaction.coordinator_class", "jta");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.put("hibernate.format_sql", true);
		hibernateProperties.put("hibernate.user_sql_comments", true);
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.max_fetch_depth", 3);
		hibernateProperties.put("hibernate.jdbc.batch_size", 10);
		hibernateProperties.put("hibernate.jdbc.fetch_size", 50);

		return hibernateProperties;
	}

	@Bean(initMethod = "init", destroyMethod = "shutdownForce")
	public UserTransactionService userTransactionService() {
		Properties properties = new Properties();
		properties.put("com.atomikos.icatch.service",
				"com.atomikos.icatch.standalone.UserTransactionServiceFactory");

		return new UserTransactionServiceImp(properties);
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	@DependsOn("userTransactionService")
	public UserTransactionManager atomikosTransactionManager() {
		UserTransactionManager transactionManager = new UserTransactionManager();
		transactionManager.setStartupTransactionService(false);
		transactionManager.setForceShutdown(true);

		return transactionManager;
	}

	@Bean
	@DependsOn("userTransactionService")
	public UserTransaction atomikosUserTransaction() {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		try {
			userTransactionImp.setTransactionTimeout(300);
		} catch (SystemException e) {
			System.err.println(e.getMessage());
			return null;
		}

		return userTransactionImp;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JtaTransactionManager transactionManager = new JtaTransactionManager();
		transactionManager.setTransactionManager(atomikosTransactionManager());
		transactionManager.setUserTransaction(atomikosUserTransaction());

		return transactionManager;
	}
}
