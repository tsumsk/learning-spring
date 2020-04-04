package tacos.message.rabbitmq;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.jpa.data.Order;
import tacos.message.OrderMessagingService;

@Service
public class RabbitmqOrderMessagingService implements OrderMessagingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(
            "tacocloud.order",
            order,
            message -> {
                MessageProperties properties = message.getMessageProperties();
                properties.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            });
    }
}
