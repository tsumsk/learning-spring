import jpa.example.AppConfig;
import jpa.example.SingerService;
import jpa.example.SingerSummaryService;
import jpa.example.entities.Album;
import jpa.example.entities.Singer;
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

public class JpaExampleTest {
	private static Logger logger = LoggerFactory.getLogger(JpaExampleTest.class);

	@Test
	public void testFindAll() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingers(singerService.findAll());
		ctx.close();
	}

	private void listSingers(List<Singer> singers) {
		for (Singer singer : singers) {
			logger.info(singer.toString());
		}
	}

	@Test
	public void testFindAllByNativeQuery() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingers(singerService.findAllByNativeQuery());
		ctx.close();
	}

	@Test
	public void testFindByCriteriaQuery() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingersWithAlbum(singerService.findByCriteriaQuery(null, "liu"));
		ctx.close();
	}

	@Test
	public void testFindAllWithAlbum() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingersWithAlbum(singerService.findAllWithAlbum());
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
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingersWithAlbum(Collections.singletonList(singerService.findById(4L)));
		ctx.close();
	}

	@Test
	public void testDisplaySingerSummary() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerSummaryService singerSummaryService = ctx.getBean("singerSummaryService",
			SingerSummaryService.class);
		singerSummaryService.displaySingerSummary();
		ctx.close();
	}

	@Test
	public void testDisplaySingerSummaryV2() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerSummaryService singerSummaryService = ctx.getBean("singerSummaryService",
			SingerSummaryService.class);
		singerSummaryService.displaySingerSummaryV2();
		ctx.close();
	}

	@Test
	public void testInsert() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

		Singer singer = new Singer();
		singer.setFirstName("anxin");
		singer.setLastName("liu");
		singer.setBirthDate(new Date(new GregorianCalendar(2019, 12, 19).getTime().getTime()));

		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new Date(new GregorianCalendar(2021, 1, 8).getTime().getTime()));
		singer.addAlbum(album);

		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(new Date(new GregorianCalendar(2021, 1, 8).getTime().getTime()));
		singer.addAlbum(album);

		singerService.save(singer);
		assertNotNull(singer.getId());

		listSingersWithAlbum(singerService.findAllWithAlbum());

		ctx.close();
	}

	@Test
	public void testDelete() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);

		Singer singer = singerService.findById(3L);
		singerService.delete(singer);

		listSingersWithAlbum(singerService.findAllWithAlbum());

		ctx.close();
	}

}
