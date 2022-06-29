package com.project.travelAgency.service;

import com.project.travelAgency.dto.CartDetailDto;
import com.project.travelAgency.dto.CartDto;
import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.model.entity.*;
import com.project.travelAgency.model.repository.CartRepository;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.model.repository.UserRepository;
import com.project.travelAgency.service.impl.CartServiceImpl;
import com.project.travelAgency.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CartServiceImplTest {


    private TourRepository tourRepository;
    private CartRepository cartRepository;
    private UserServiceImpl userService;
    private CartServiceImpl cartService;
    private UserRepository userRepository;
    private OrderService orderService;
    private CartServiceImpl cartService1;


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
        userRepository = Mockito.mock(UserRepository.class);
        userService = Mockito.mock(UserServiceImpl.class);
        cartService = Mockito.mock(CartServiceImpl.class);
        cartService1 = new CartServiceImpl(cartRepository,tourRepository,userService,orderService);

    }

    @Test
    void checkCreateCart() {
        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(expectedUser);

        List<Tour> tours = new ArrayList<>();
        Tour tour = Tour.builder().id(1L).build();
        tours.add(0, tour);
        List<Long> toursIds = tours.stream().map(x -> x.getId()).collect(Collectors.toList());
        Mockito.when(tourRepository.findAll()).thenReturn(tours);

        User actualUser = userService.findByName(name);
        List<Tour> actualTours = tourRepository.findAll();
        List<Long> actualToursIds = actualTours.stream().map(x -> x.getId()).collect(Collectors.toList());

        Cart actualCart = cartService.createCart(actualUser, actualToursIds);
        Cart cart = cartService.createCart(expectedUser, toursIds);

        Assertions.assertEquals(cart, actualCart);
    }

    @Test
    void checkSave() {
        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(expectedUser);

        List<Tour> tours = new ArrayList<>();
        Tour tour = Tour.builder().id(1L).build();
        tours.add(0, tour);

        Cart cart = Cart.builder()
                .id(1L)
                .user(expectedUser)
                .tours(tours)
                .build();


        boolean result = cartService1.save(cart);

        Assertions.assertTrue(result);
        Mockito.verify(cartRepository).save(Mockito.any());
    }

    @Test
    void checkGetCartByUser() {
        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(expectedUser);

        CartDetailDto cartDetail = CartDetailDto.builder()
                .typeOfTour(TypeOfTour.Vacation)
                .country(new Country(new CountryDto("Italy", "Milan")))
                .days(7)
                .price(new BigDecimal(100.00))
                .amount(BigDecimal.valueOf(1))
                .sum(100.00)
                .build();

        List<CartDetailDto> cartDetails = new ArrayList<>();
        cartDetails.add(0, cartDetail);

        CartDto cartDto = CartDto.builder()
                .amountTours(1)
                .sum(100.00)
                .cartDetails(cartDetails)
                .build();

        User actualUser = userService.findByName(name);

        Mockito.when(cartService.getCartByUser("user666")).thenReturn(cartDto);

        CartDto actualCartDto = cartService.getCartByUser(actualUser.getName());

        Assertions.assertEquals(cartDto, actualCartDto);
    }

    @Test
    void checkDeleteTourByUser() {
        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();

        List<Tour> tours = new ArrayList<>();
        Tour tour = Tour.builder().id(1L).build();
        Tour tour1 = Tour.builder().id(2L).build();
        tours.add(0, tour);
        tours.add(1, tour1);

        Mockito.when(tourRepository.findAll()).thenReturn(tours);
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(expectedUser);

        Cart cart = Cart.builder()
                .user(userService.findByName(name))
                .tours(tourRepository.findAll())
                .build();

        cart.getTours().remove(tour);

        List<Tour> actual = cart.getTours();
        List<Tour> expected = tourRepository.findAll();

        Assertions.assertEquals(expected, actual);
    }
}
