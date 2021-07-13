package tacos.message.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tacos.jpa.data.Order;
import tacos.message.OrderSender;

@Service
public class KafkaOrderSender implements OrderSender {

    //@Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.send("tacocloud.order", order);
    }
}
