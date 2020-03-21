package tacos.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class TacoOrderConfig {

    @Min(value = 5, message = "must between 5 and 20")
    @Max(value = 20, message = "must between 5 and 20")
    private int pageSize = 10;
}
