package com.project.travelAgency.service;

import com.project.travelAgency.model.dto.TourDto;

import java.util.List;

public interface TourService {

    List<TourDto> getAll();
    void addToUserCart(Long tourId,String username);// добавляем тур по id к определенному user
}
