package tacos.jdbc.repository;

import tacos.jdbc.data.Order;

public interface OrderRepository {
    Order save(Order order);
}
