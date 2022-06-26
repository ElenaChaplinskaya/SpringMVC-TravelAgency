package com.project.travelAgency.service.impl;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        logger.info("Save User`s Order in DB");
        return orderRepository.save(order);
    }
}
