package tacos.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.data.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
