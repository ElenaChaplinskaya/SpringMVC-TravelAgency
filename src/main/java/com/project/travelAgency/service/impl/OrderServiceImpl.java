package com.project.travelAgency.service.impl;


import com.project.travelAgency.dto.OrderDetailDto;
import com.project.travelAgency.dto.OrderDto;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.OrderRepository;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.service.OrderService;
import com.project.travelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TourRepository tourRepository;
    private final UserService userService;


    @Override
    public Order createOrder(User user, List<Long> tourIds) { //метод отработает, когда нужно будет добавлять туры в корзину
        Order order = new Order(); //создает новую корзину
        order.setUser(user); // сеттим юзера в корзину
        List<Tour> tourList = getCollectRefToursByIds(tourIds); //у нас есть список туров, который лежит в определенной корзине определенного пользователя
        order.setTours(tourList);
        return orderRepository.save(order);
    }

    //getOne берет ссылку на объект, findById- берет сам объект
    private List<Tour> getCollectRefToursByIds(List<Long> tourIds) {
        return tourIds.stream()
                .map(tourRepository::getOne)
                .collect(Collectors.toList());
    }

    //туры мы берем по id
    @Override
    public void addTours(Order order, List<Long> tourIds) {
        List<Tour> tours = order.getTours();
        List<Tour> newTourList = tours == null ? new ArrayList<>() : new ArrayList<>(tours);
        newTourList.addAll(getCollectRefToursByIds(tourIds));
        order.setTours(newTourList);
        orderRepository.save(order);

    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderByUser(String name) {
        User user = userService.findByName(name); // берем юзера из БД
        if (user == null || user.getOrder() == null) { // если нет юзера или нет юзера с определ корзиной
            return new OrderDto(); // возвращаем новую корзину ДТО
        }

        OrderDto orderDto = new OrderDto();  // создаем новую корзину ДТО
        Map<Long, OrderDetailDto> mapByTourId = new HashMap<>();// создаем список туров где по id тура у нас есть определенное описание тура

        List<Tour> tours = user.getOrder().getTours(); // создаем список туров у определенного юзера
        for (Tour tour : tours) {
            OrderDetailDto detail = mapByTourId.get(tour.getId());  // detail это тур по id c описанием
            if (detail == null) {
                mapByTourId.put(tour.getId(), new OrderDetailDto(tour));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(tour.getPrice().toString()));
            }
        }

        orderDto.setOrderDetails(new ArrayList<>(mapByTourId.values()));
        orderDto.aggregate();

        return orderDto;
    }

//    @Override
//    public void deleteTourByUser(Long cartId, Long tourId) {
//        Cart cart = cartRepository.getById(cartId);
//        Tour tour=tourRepository.getById(tourId);
//        cart.removeTour(tour);
//        cartRepository.save(cart);
//
//    }

    @Override
    public OrderDto deleteTourByUser(String name, Long tourId) {
        Order order = orderRepository.findByUserName(name);
        Tour tour = order.getTours().stream().filter(t -> tourId.equals(t.getId())).findFirst().orElseThrow(NoSuchElementException::new);
        order.getTours().remove(tour);
        Order savedOrder = orderRepository.save(order);
        OrderDto orderDto = new OrderDto();
        Map<Long, OrderDetailDto> mapByTourId = new HashMap<>();
        List<Tour> tours = savedOrder.getTours();
        for (Tour t : tours) {
            OrderDetailDto detail = mapByTourId.get(tour.getId());
            if (detail == null) {
                mapByTourId.put(tour.getId(), new OrderDetailDto(tour));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(tour.getPrice().toString()));
            }
        }

        orderDto.setOrderDetails(new ArrayList<>(mapByTourId.values()));
        orderDto.aggregate();

        return orderDto;

    }

}
