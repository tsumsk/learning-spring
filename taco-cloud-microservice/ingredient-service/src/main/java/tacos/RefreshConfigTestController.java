package tacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConfigurationProperties(prefix = "greeting")
@RestController
public class RefreshConfigTestController {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@GetMapping("/hello")
	public String message() {
		return message;
	}
}
