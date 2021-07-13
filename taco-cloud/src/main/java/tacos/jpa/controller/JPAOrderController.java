package tacos.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.config.TacoOrderConfig;
import tacos.jpa.data.Order;
import tacos.jpa.repository.OrderRepository;
import tacos.message.OrderSender;
import tacos.security.data.User;
import tacos.security.repository.UserRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/jpa/orders")
@SessionAttributes("order")
public class JPAOrderController {

    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TacoOrderConfig tacoOrderConfig;

    @Autowired
    @Qualifier("jmsOrderMessagingService")
    private OrderSender jmsOrderSender;

    @Autowired
    @Qualifier("rabbitmqOrderMessagingService")
    private OrderSender rabbitmqOrderSender;

    @Autowired
    @Qualifier("kafkaOrderMessagingService")
    private OrderSender kafkaOrderSender;

    @Autowired
    public JPAOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "jpaOrderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

        if (errors.hasErrors()) {
            return "jpaOrderForm";
        }

        log.info("Order submitted: " + order);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        order.setUser((User)authentication.getPrincipal());

        orderRepository.save(order);

        // jmsOrderMessagingService.sendOrder(order);

        rabbitmqOrderSender.sendOrder(order);

        // kafkaOrderMessagingService.sendOrder(order);

        // close session
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String orderOfUser(@AuthenticationPrincipal User user, Model model) {

        Pageable pageable = PageRequest.of(0, tacoOrderConfig.getPageSize());

        model.addAttribute("orders", orderRepository.findByUserOrderByCreatedAtDesc(user, pageable));

        return "orderList";
    }
}
