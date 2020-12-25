import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.jdbc.stored.function.example.Config;
import spring.jdbc.stored.function.example.SingerDao;

import static org.junit.Assert.assertNotNull;

public class SpringJdbcStoredFunctionExampleTest {
	@Test
	public void testFirstNameById() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);

		String firstName = singerDao.firstNameById(5L);
		System.out.println(firstName);

		ctx.close();
	}
}
