import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.jdbc.sql.update.example.Config;
import spring.jdbc.sql.update.example.Singer;
import spring.jdbc.sql.update.example.SingerDao;

import java.sql.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertNotNull;

public class SpringJdbcSqlUpdateExampleTest {
	@Test
	public void testInsert() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);

		Singer singer = new Singer();
		singer.setFirstName("Xu");
		singer.setLastName("Liu");
		singer.setBirthDate(new Date(
			(new GregorianCalendar(1989, 9, 29)).getTime().getTime()));

		singerDao.insert(singer);

		ctx.close();
	}

	@Test
	public void testUpdate() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		SingerDao singerDao = ctx.getBean("jdbcSingerDao", SingerDao.class);
		assertNotNull(singerDao);

		Singer singer = new Singer();
		singer.setId(1L);
		singer.setFirstName("John Clayton");
		singer.setLastName("Mayer");
		singer.setBirthDate(new Date(
			(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));

		singerDao.update(singer);

		ctx.close();
	}
}
