import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.jdbc.template.example.Config;
import spring.jdbc.template.example.ConfigV2;
import spring.jdbc.template.example.SingerDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpringJdbcTemplateExampleTest {
	@Test
	public void test() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);
		String singerName = singerDao.findNameById(1l);
		assertTrue("John Mayer".equals(singerName));
		ctx.close();
	}

	@Test
	public void testV2() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigV2.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);
		String singerName = singerDao.findNameByIdV2(1l);
		assertTrue("John Mayer".equals(singerName));
		ctx.close();
	}
}
