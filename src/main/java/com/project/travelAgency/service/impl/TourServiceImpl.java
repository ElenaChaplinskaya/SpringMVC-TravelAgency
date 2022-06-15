package com.project.travelAgency.service.impl;

import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.dto.TypeOfTourDto;
import com.project.travelAgency.model.entity.*;
import com.project.travelAgency.model.mapper.TourMapper;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.service.OrderService;
import com.project.travelAgency.service.TourService;
import com.project.travelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {


    private final TourMapper mapper = TourMapper.MAPPER;
    private final TourRepository tourRepository;
    private final UserService userService;
    private final OrderService orderService;


    @Override
    public List<TourDto> getAll() {
      return   tourRepository.findAll().stream()
              .map(TourDto::new)
              .collect(Collectors.toList());
//        return mapper.fromTourList(tourRepository.findAll()); // косяк с мапперами
    }

    @Override
    public void addToUserOrder(Long tourId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User " + username + " not found");
        }
        Order order = user.getOrder(); // ищем заказ у юзера
        if (order == null) {
            Order newOrder = orderService.createOrder(user, Collections.singletonList(tourId));
            user.setOrder(newOrder);
            userService.save(user);
        } else {
            orderService.addTours(order, Collections.singletonList(tourId));
        }
    }

    @Override
    public Tour findById(long id) {
        return tourRepository.findById(id).get();
    }

    @Override
    public Tour save(TourDto tourDto, TypeOfTourDto typeOfTourDto, CountryDto countryDto) {


        Tour tour = Tour.builder()
                .typeOfTour(new TypeOfTour(typeOfTourDto))
                .country(new Country(countryDto))
                .days(tourDto.getDays())
                .price(tourDto.getPrice())
                .status(tourDto.getStatus())
                .build();
//
//        Tour tour = Tour.builder()
//                .typeOfTour(tourDto.getTypeOfTourDto())
//                .country(tourDto.getCountry())
//                .days(tourDto.getDays())
//                .price(tourDto.getPrice())
//                .status(tourDto.getStatus())
//                .build();
        tourRepository.save(tour);
        return tour;
    }

}

