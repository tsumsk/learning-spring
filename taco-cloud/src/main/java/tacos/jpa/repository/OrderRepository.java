package tacos.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.jpa.data.Order;
import tacos.security.data.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

    public List<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
