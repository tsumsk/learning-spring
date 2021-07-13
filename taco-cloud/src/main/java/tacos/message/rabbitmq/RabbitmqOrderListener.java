package tacos.message.rabbitmq;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.integration.FileWriterGateway;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class RabbitmqOrderListener {

    @Autowired
    private FileWriterGateway fileWriterGateway;

    @RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(Order order) {
        log.info("order: " + order);
        fileWriterGateway.writeToFile("tacocloud.order", JSON.toJSONString(order));
    }
}
