package tacos.message.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class KafkaOrderListener {

    //@KafkaListener(topics = "tacocloud.order")
    public void receiveOrder(Order order) {
        log.info("order: " + order);
    }
}
