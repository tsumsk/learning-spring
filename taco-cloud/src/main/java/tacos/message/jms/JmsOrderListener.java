package tacos.message.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class JmsOrderListener {
	@JmsListener(destination = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		log.info(order.toString());
	}
}
