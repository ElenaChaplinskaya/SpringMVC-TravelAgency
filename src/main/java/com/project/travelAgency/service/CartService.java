package com.project.travelAgency.service;

import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.User;

import java.util.List;

public interface CartService {

    Cart createCart(User user, List<Integer> tourIds);

    void addTours(Cart cart,List<Integer> tourIds);
}
