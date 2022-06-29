package com.project.travelAgency.service;

import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.*;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.service.impl.OrderServiceImpl;
import com.project.travelAgency.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImplTest {

    private UserServiceImpl userService;
    private OrderRepository orderRepository;
    private OrderServiceImpl orderService;
    private TourRepository tourRepository;

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
        orderRepository = Mockito.mock(OrderRepository.class);
        userService=Mockito.mock(UserServiceImpl.class);
        tourRepository=Mockito.mock(TourRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void checkSaveOrder(){

        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();
        Mockito.when(userService.findByName(Mockito.anyString())).thenReturn(expectedUser);

        List<Tour> tours = new ArrayList<>();
        Tour tour = Tour.builder().id(1L).build();
        tours.add(0, tour);

        Mockito.when(tourRepository.findAll()).thenReturn(tours);

        Order order = Order.builder()
                .sum(new BigDecimal(100.00))
                .user(expectedUser)
                .created(LocalDateTime.MAX)
                .status(OrderStatus.NEW)
                .build();

        OrderDetails orderDetails=OrderDetails.builder()
                .order(order)
                .tour(tour)
                .amount(new BigDecimal(1.0))
                .price(new BigDecimal(100.00))
                .build();
        List<OrderDetails> details=new ArrayList<>();
        details.add(0,orderDetails);

        order.setDetails(details);

        boolean result = orderService.saveOrder(order);
        Assertions.assertTrue(result);
        Mockito.verify(orderRepository).save(Mockito.any());
    }
}
