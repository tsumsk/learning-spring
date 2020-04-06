package tacos.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class RabbitmqOrderListener {

    @RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(Order order) {
        log.info("order: " + order);
    }
}
