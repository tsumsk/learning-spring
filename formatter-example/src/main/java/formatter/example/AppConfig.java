package formatter.example;

import java.net.URL;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "formatter.example")
public class AppConfig {
  @Autowired
  private ApplicationConversionServiceFactoryBean applicationConversionServiceFactoryBean;

  @Bean
  public Singer john() throws Exception {
    Singer singer = new Singer();
    singer.setFirstName("John");
    singer.setLastName("Mayer");
    singer.setBirthDate(applicationConversionServiceFactoryBean.getDateTimeFormatter().parse(
        "1977-10-16", Locale.ENGLISH));
    singer.setPersonalSite(new URL("http://johnmayer.com"));
    return singer;
  }
}
