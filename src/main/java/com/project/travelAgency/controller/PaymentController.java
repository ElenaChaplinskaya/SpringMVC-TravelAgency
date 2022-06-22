package com.project.travelAgency.controller;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.OrderStatus;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping
    public String makeAPayment(Model model){

        return "payment";
    }

    @PostMapping
    public String makeAPayment(Principal principal){

        List<Order>orders = orderRepository.findLastByUserName(principal.getName());
        for (Order order:orders) {
            if (order.getStatus()==OrderStatus.NEW) {
                order.setStatus(OrderStatus.PAID);
                orderService.saveOrder(order);
            }
        }

        return"redirect:/order";
    }

}
