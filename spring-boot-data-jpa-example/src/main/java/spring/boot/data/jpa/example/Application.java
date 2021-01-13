package spring.boot.data.jpa.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.data.jpa.example.entities.Singer;

import java.util.List;

@SpringBootApplication(scanBasePackages = "spring.boot.data.jpa.example")
public class Application implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private SingerRepository singerRepository;

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.in.read();

		ctx.close();
	}

	@Transactional(readOnly = true)
	@Override
	public void run(String... args) throws Exception {
		List<Singer> singers = singerRepository.findByFirstName("xu");
		listSingers(singers);
	}

	private void listSingers(List<Singer> singers) {
		for (Singer singer : singers) {
			logger.info(singer.toString());
		}
	}
}
