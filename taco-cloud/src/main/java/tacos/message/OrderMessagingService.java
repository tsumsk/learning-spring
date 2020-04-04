package tacos.message;

import tacos.jpa.data.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
