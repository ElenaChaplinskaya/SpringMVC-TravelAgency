package com.project.travelAgency.service.impl;

import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.dto.TypeOfTourDto;
import com.project.travelAgency.model.entity.*;
import com.project.travelAgency.model.mapper.TourMapper;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.service.CartService;
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
    private final CartService cartService;


    @Override
    public List<TourDto> getAll() {
        return tourRepository.findAll().stream()
                .map(TourDto::new)
                .collect(Collectors.toList());
//        return mapper.fromTourList(tourRepository.findAll()); // косяк с мапперами
    }

    @Override
    public void addToUserCart(Long tourId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User " + username + " not found");
        }
        Cart cart = user.getCart(); // ищем заказ у юзера
        if (cart == null) {
            Cart newCart = cartService.createCart(user, Collections.singletonList(tourId));
            user.setCart(newCart);
            userService.save(user);
        } else {
            cartService.addTours(cart, Collections.singletonList(tourId));
        }
    }

    @Override
    public Tour getById(long id) {

        return tourRepository.getById(id);
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

        tourRepository.save(tour);
        return tour;
    }

}

