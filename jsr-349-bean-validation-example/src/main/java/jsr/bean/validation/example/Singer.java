package jsr.bean.validation.example;

import java.net.URL;
import java.util.regex.Pattern;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@ToString
public class Singer {
  @NotNull
  @Size(min = 2, max = 60)
  private String firstName;

  private String lastName;

  @NotNull
  private DateTime birthDate;

  private URL personalSite;

  @AssertTrue(message = "invalid personal site url")
  public boolean isValidUrl() {
    if (personalSite != null
        && Pattern.matches("http://www\\.*", personalSite.toString())) {
      return true;
    }

    return false;
  }
}
