package com.project.travelAgency.service;

import com.project.travelAgency.model.dto.CartDto;
import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.User;

import java.util.List;

public interface CartService {

    Cart createCart(User user, List<Long> tourIds);

    void addTours(Cart cart,List<Long> tourIds);

    CartDto getCartByUser(String name);// поиск корзины по user
}
