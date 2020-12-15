import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.jdbc.template.example.Config;
import spring.jdbc.template.example.SingerDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpringJdbcTemplateExampleTest {
	@Test
	public void test() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		testDao(ctx.getBean("jdbcSingerDao", SingerDao.class));
		ctx.close();
	}

	private void testDao(SingerDao singerDao) {
		assertNotNull(singerDao);
		String singerName = singerDao.findNameById(1l);
		assertTrue("John Mayer".equals(singerName));
	}
}
