package com.project.travelAgency.service.impl;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.service.OrderService;
import com.project.travelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void setTotalSum(String name){

        User user =userService.findByName(name);

        List<Order> orders = orderRepository.findAll();
        BigDecimal totalSum = new BigDecimal(orders.stream()
                .map(order -> order.getSum().multiply(user.getDiscount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

    }

}
