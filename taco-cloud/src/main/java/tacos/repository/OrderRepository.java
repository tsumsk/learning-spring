package tacos.repository;

import tacos.repository.data.Order;

public interface OrderRepository {
    Order save(Order order);
}
