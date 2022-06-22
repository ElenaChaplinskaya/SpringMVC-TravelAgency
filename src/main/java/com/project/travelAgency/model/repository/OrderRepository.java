package com.project.travelAgency.model.repository;

import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUserName(String name);

    List<Order> findLastByUserName(String name);


}
