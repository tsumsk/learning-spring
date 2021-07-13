package tacos.jdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.jdbc.data.Order;
import tacos.jdbc.repository.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/jdbc/orders")
@SessionAttributes("order")
public class JDBCOrderController {

    private OrderRepository orderRepository;

    @Autowired
    public JDBCOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "jdbcOrderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "jdbcOrderForm";
        }

        log.info("Order submitted: " + order);
        orderRepository.save(order);

        // close session
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
