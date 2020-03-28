package tacos.jpa.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.config.TacoOrderConfig;
import tacos.jpa.data.Order;
import tacos.jpa.repository.OrderRepository;
import tacos.security.data.User;

@Slf4j
@RestController
@RequestMapping(path = "/orders", produces = "application/json")
public class JPAOrderController {

    private OrderRepository orderRepository;

    @Autowired
    private TacoOrderConfig tacoOrderConfig;

    @Autowired
    public JPAOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order processOrder(@RequestBody @Valid Order order, @AuthenticationPrincipal User user) {

        log.info("Order submitted: " + order);

        order.setUser(user);

        return orderRepository.save(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> orderOfUser(@AuthenticationPrincipal User user) {

        Pageable pageable = PageRequest.of(0, tacoOrderConfig.getPageSize());

        List<Order> orders = orderRepository.findByUserOrderByCreatedAtDesc(user, pageable);

        if (CollectionUtils.isEmpty(orders)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public ResponseEntity<Order> patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        Optional<Order> result = orderRepository.findById(orderId);

        if (!result.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Order order = result.get();

        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }

        order = orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
