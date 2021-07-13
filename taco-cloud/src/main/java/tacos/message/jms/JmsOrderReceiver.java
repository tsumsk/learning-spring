package tacos.message.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.jpa.data.Order;

@Slf4j
@Component
public class JmsOrderReceiver {
	private JmsTemplate jmsTemplate;

	@Autowired
	public JmsOrderReceiver(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Order receiveOrder() {
		return (Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
	}
}
