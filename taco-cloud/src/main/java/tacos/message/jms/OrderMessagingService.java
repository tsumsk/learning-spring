package tacos.message.jms;

import tacos.jpa.data.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
