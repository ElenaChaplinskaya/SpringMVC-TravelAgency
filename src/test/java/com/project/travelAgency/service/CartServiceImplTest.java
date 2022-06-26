package com.project.travelAgency.service;

import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.CartRepository;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.model.repository.UserRepository;
import com.project.travelAgency.service.impl.CartServiceImpl;
import com.project.travelAgency.service.impl.OrderServiceImpl;
import com.project.travelAgency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartServiceImplTest {


    private TourRepository tourRepository;
    private CartRepository cartRepository;
    private OrderServiceImpl orderService;
    private UserServiceImpl userService;
    private CartServiceImpl cartService;

    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Before All tests");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        cartRepository = Mockito.mock(CartRepository.class);
        tourRepository = Mockito.mock(TourRepository.class);
        cartService = new CartServiceImpl(cartRepository, tourRepository, userService, orderService);

    }

//    @Test
//    void checkCreateCart() {
//        String name = "user666";
//        User expectedUser = User.builder().id(1).name(name).build();
//        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);
//        List<Tour> tours = new ArrayList<>();
//        Tour tour = Tour.builder().id(1L).build();
//        tours.add(0, tour);
//        Cart cart = new Cart();
//        cart.setUser(expectedUser);
//        cart.setTours(tours);
//
//        List<Long> tourIds=new ArrayList<>();
//        tourIds.stream().map(tours::forEach).collect(Collectors.toList());
//        User actualUser = userService.findByName(name);
//        List<Tour> actualTours = tourRepository.findAll();
//        Cart actualCart = cartService.createCart(actualUser, actualTours);
//
//    }
}
