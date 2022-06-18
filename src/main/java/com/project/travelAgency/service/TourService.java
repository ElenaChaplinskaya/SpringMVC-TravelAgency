package com.project.travelAgency.service;

import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.dto.TypeOfTourDto;
import com.project.travelAgency.model.entity.Status;
import com.project.travelAgency.model.entity.Tour;

import java.util.List;

public interface TourService {

    List<TourDto> getAll();

    void addToUserCart(Long tourId, String username);// добавляем тур по id к определенному user

    Tour getById(long id);

    Tour save(TourDto tourDto, TypeOfTourDto typeOfTourDto, CountryDto countryDto);

}
