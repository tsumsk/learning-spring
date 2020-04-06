package tacos.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class RabbitmqOrderReceiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void receiveOrder() {
        Order order = rabbitTemplate.receiveAndConvert(
            "tacocloud.order",
            new ParameterizedTypeReference<Order>() {}
        );

        log.info("order: " + order);
    }
}
