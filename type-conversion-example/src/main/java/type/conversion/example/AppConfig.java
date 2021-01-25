package type.conversion.example;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "type.conversion.example")
public class AppConfig {

  @Value("${date.format.pattern}")
  private String dateFormatPattern;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public Singer john(@Value("${countrySinger.firstName}") String firstName,
      @Value("${countrySinger.lastName}") String lastName,
      @Value("${countrySinger.birthDate}") DateTime birthDate,
      @Value("${countrySinger.personalSite}") URL personalSite) {
    Singer singer = new Singer();
    singer.setFirstName(firstName);
    singer.setLastName(lastName);
    singer.setBirthDate(birthDate);
    singer.setPersonalSite(personalSite);
    return singer;
  }

  @Bean
  public StringToDateTimeConverter stringToDateTimeConverter() {
    StringToDateTimeConverter converter = new StringToDateTimeConverter();
    converter.setDatePattern(dateFormatPattern);
    converter.init();
    return converter;
  }

  @Bean
  public ConversionServiceFactoryBean conversionService() {
    ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();

    Set<Converter> converters = new HashSet<>();
    converters.add(stringToDateTimeConverter());

    conversionServiceFactoryBean.setConverters(converters);
    conversionServiceFactoryBean.afterPropertiesSet();

    return conversionServiceFactoryBean;
  }



}
