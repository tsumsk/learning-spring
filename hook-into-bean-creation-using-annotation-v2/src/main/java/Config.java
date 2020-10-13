import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class Config {
    @Lazy
    @Bean(initMethod = "init")
    Singer singerOne() {
        Singer singer = new Singer();
        singer.setName("John Mayer");
        singer.setAge(39);
        return singer;
    }

    @Lazy
    @Bean(initMethod = "init")
    Singer singerTwo() {
        Singer singer = new Singer();
        singer.setAge(72);
        return singer;
    }

    @Lazy
    @Bean(initMethod = "init")
    Singer singerThree() {
        Singer singer = new Singer();
        singer.setName("John Mayer");
        return singer;
    }
}
