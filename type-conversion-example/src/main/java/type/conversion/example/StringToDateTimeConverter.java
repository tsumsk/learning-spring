package type.conversion.example;

import javax.annotation.PostConstruct;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class StringToDateTimeConverter implements Converter<String, DateTime> {
  private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

  private DateTimeFormatter dateFormatter;

  private String datePattern = DEFAULT_DATE_PATTERN;

  public String getDatePattern() {
    return datePattern;
  }

  public void setDatePattern(String datePattern) {
    this.datePattern = datePattern;
  }

  @PostConstruct
  public void init() {
    dateFormatter = DateTimeFormat.forPattern(datePattern);
  }

  @Override
  public DateTime convert(String dateString) {
    return dateFormatter.parseDateTime(dateString);
  }
}
