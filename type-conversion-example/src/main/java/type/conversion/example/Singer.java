package type.conversion.example;

import java.net.URL;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@ToString
public class Singer {
  private String firstName;

  private String lastName;

  private DateTime birthDate;

  private URL personalSite;
}
