package com.tacocloud.controller;

import com.tacocloud.configuration.OrderProps;
import com.tacocloud.entity.TacoOrder;
import com.tacocloud.entity.User;
import com.tacocloud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderProps orderProps;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderProps orderProps, OrderRepository orderRepository) {
        this.orderProps = orderProps;
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        order.setPlacedAt(new Date());

        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findTacoOrderByPlacedAt(user, pageable));
        return "orderList";
    }
}
