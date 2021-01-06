import hibernate.example.AppConfig;
import hibernate.example.SingerDao;
import hibernate.example.entities.Album;
import hibernate.example.entities.Singer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class HibernateExampleTest {
	private static Logger logger = LoggerFactory.getLogger(HibernateExampleTest.class);

	@Test
	public void testFindAll() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
		listSingers(singerDao.findAll());
		ctx.close();
	}

	private void listSingers(List<Singer> singers) {
		for (Singer singer : singers) {
			logger.info(singer.toString());
		}
	}

	@Test
	public void testFindAllWithAlbum() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
		listSingersWithAlbum(singerDao.findAllWithAlbum());
		ctx.close();
	}

	private void listSingersWithAlbum(List<Singer> singers) {
		for (Singer singer : singers) {
			logger.info(singer.toString());

			if (singer.getAlbums() != null) {
				for (Album album : singer.getAlbums()) {
					logger.info(album.toString());
				}
			}
		}
	}

	@Test
	public void testFindById() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);
		listSingersWithAlbum(Collections.singletonList(singerDao.findById(2L)));
		ctx.close();
	}

	@Test
	public void testInsert() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);

		Singer singer = new Singer();
		singer.setFirstName("xu");
		singer.setLastName("liu");
		singer.setBirthDate(new Date(new GregorianCalendar(1989, 9, 29).getTime().getTime()));

		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new Date(new GregorianCalendar(2021, 1, 6).getTime().getTime()));
		singer.addAlbum(album);

		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(new Date(new GregorianCalendar(2021, 1, 6).getTime().getTime()));
		singer.addAlbum(album);

		singerDao.save(singer);
		assertNotNull(singer.getId());

		listSingersWithAlbum(singerDao.findAllWithAlbum());

		ctx.close();
	}

	@Test
	public void testUpdate() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);

		Singer singer = singerDao.findById(1L);
		singer.setFirstName("John Clayton");
		singer.getAlbums().remove(singer.getAlbums().toArray()[0]);

		singerDao.save(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());

		ctx.close();
	}

	@Test
	public void testDelete() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);

		Singer singer = singerDao.findById(2L);
		singerDao.delete(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());

		ctx.close();
	}
}
