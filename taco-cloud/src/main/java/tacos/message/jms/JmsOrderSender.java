package tacos.message.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.jpa.data.Order;
import tacos.message.OrderSender;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderSender implements OrderSender {

    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.convertAndSend(
            "tacocloud.order.queue",
            order,
            this::addOrderSource
        );
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
