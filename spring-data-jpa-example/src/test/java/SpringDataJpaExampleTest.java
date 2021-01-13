import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import spring.data.jpa.example.AlbumService;
import spring.data.jpa.example.AppConfig;
import spring.data.jpa.example.SingerService;
import spring.data.jpa.example.entities.Album;
import spring.data.jpa.example.entities.Singer;

import java.util.List;

public class SpringDataJpaExampleTest {
	private static Logger logger = LoggerFactory.getLogger(SpringDataJpaExampleTest.class);

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
	public void testFindByFirstName() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingers(singerService.findByFirstName("xu"));
		ctx.close();
	}

	@Test
	public void testFindByFirstNameAndLastName() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerService singerService = ctx.getBean("jpaSingerService", SingerService.class);
		listSingers(singerService.findByFirstNameAndLastName("xu", "liu"));
		ctx.close();
	}

	@Test
	public void testFindByTitle() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		AlbumService albumService = ctx.getBean("jpaAlbumService", AlbumService.class);
		listAlbums(albumService.findByTitle("The"));
		ctx.close();
	}

	private void listAlbums(List<Album> albums) {
		for (Album album : albums) {
			logger.info(album.toString());
		}
	}
}
