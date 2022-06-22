package com.project.travelAgency.service.impl;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
