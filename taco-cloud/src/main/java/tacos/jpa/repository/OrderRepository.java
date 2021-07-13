package tacos.jpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.jpa.data.Order;
import tacos.security.data.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
