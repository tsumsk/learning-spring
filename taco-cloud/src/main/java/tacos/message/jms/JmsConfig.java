package tacos.message.jms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.jpa.data.Order;

@Configuration
public class JmsConfig {

    @Bean
    public MappingJackson2MessageConverter jmsMessageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

        // enables the receiver to know what type to convert an incoming message to
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }
}
