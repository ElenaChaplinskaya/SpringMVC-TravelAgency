package com.project.travelAgency.service;

import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.CartRepository;
import com.project.travelAgency.model.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final TourRepository tourRepository;


    @Override
    public Cart createCart(User user, List<Integer> tourIds) { //метод отработает, когда нужно будет добавлять туры в корзину
        Cart cart = new Cart(); //создает новую корзину
        cart.setUser(user); // сеттим юзера в корзину
        List<Tour> tourList = getCollectRefToursByIds(tourIds); //у нас есть список туров, который лежит в определенной корзине определенного пользователя

        return cartRepository.save(cart);
    }

    //getOne берет ссылку на объект, findById- берет сам объект
    private List<Tour> getCollectRefToursByIds(List<Integer> tourIds) {
        return tourIds.stream()
                .map(tourRepository::getOne)
                .collect(Collectors.toList());
    }

    //туры мы берем по id
    @Override
    public void addTours(Cart cart, List<Integer> tourIds) {
        List<Tour> tours = cart.getTours();
        List<Tour> newTourList = tours == null ? new ArrayList<>() : new ArrayList<>(tours);
        newTourList.addAll(getCollectRefToursByIds(tourIds));
        cart.setTours(newTourList);
        cartRepository.save(cart);

    }
}
