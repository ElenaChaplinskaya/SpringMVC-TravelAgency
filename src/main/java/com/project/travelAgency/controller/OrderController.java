package com.project.travelAgency.controller;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.service.CartService;
import com.project.travelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderRepository orderRepository;

    @GetMapping("/order")
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }

    @PostMapping("/order")
    public String commitCart(Principal principal, Model model) {
        if (principal != null) {
            cartService.commitCartToOrder(principal.getName());
            userService.getDiscount(principal.getName());
            model.addAttribute("orders", orderRepository.findAllByUserName(principal.getName()));
        }
        return "order";
    }
}
