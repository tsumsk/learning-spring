package tacos;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tacos.jpa.data.Order;
import tacos.message.rabbitmq.RabbitmqOrderMessagingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TacoCloudApplication.class)
public class RabbitMQTest {

    @Autowired
    private RabbitmqOrderMessagingService rabbitmqOrderMessagingService;

    @Test
    public void testSendMessage() {
        Order order = new Order();

        order.setId(1L);
        order.setName("name");
        order.setStreet("street");
        order.setCity("city");
        order.setState("state");
        order.setZip("zip");
        order.setCcNumber("ccNumber");
        order.setCcCVV("cvv");
        order.setCcExpiration("expiration");
        order.setCreatedAt(new Date());

        rabbitmqOrderMessagingService.sendOrder(order);
    }
}
