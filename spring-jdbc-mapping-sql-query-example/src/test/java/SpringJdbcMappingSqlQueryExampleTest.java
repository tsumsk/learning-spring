import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.jdbc.mapping.sql.query.example.Config;
import spring.jdbc.mapping.sql.query.example.Singer;
import spring.jdbc.mapping.sql.query.example.SingerDao;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SpringJdbcMappingSqlQueryExampleTest {
	@Test
	public void testFindAll() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);
		List<Singer> singers = singerDao.findAll();
		singers.forEach(System.out::println);
		ctx.close();
	}

	@Test
	public void testFindByFirstName() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);
		List<Singer> singers = singerDao.findByFirstName("John");
		singers.forEach(System.out::println);
		ctx.close();
	}
}
