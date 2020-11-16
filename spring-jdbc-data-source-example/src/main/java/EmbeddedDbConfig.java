import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedDbConfig {
	@Bean
	public DataSource dataSource() {
		try {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			return builder
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("classpath:db/schema.sql", "classpath:db/test-data.sql")
				.build();
		} catch (Exception ex) {
			return null;
		}
	}
}
