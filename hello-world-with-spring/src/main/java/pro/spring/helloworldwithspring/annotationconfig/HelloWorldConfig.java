package pro.spring.helloworldwithspring.annotationconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.spring.helloworldwithspring.HelloWorldMessageProvider;
import pro.spring.helloworldwithspring.MessageProvider;
import pro.spring.helloworldwithspring.MessageRenderer;
import pro.spring.helloworldwithspring.StandardOutMessageRenderer;

@Configuration
public class HelloWorldConfig {

    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
