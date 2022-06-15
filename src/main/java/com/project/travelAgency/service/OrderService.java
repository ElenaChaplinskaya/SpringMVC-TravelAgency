package com.project.travelAgency.service;


import com.project.travelAgency.dto.OrderDto;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, List<Long> tourIds);

    void addTours(Order order, List<Long> tourIds);

    OrderDto deleteTourByUser(String name, Long id);

    Order getById(Long id);

    void save(Order order);

    OrderDto getOrderByUser(String name);// поиск корзины по user
}
