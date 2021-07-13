package tacos.message;

import tacos.jpa.data.Order;

public interface OrderSender {
    void sendOrder(Order order);
}
