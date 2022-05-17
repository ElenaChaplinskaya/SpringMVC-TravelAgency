package com.project.travelAgency.model.mapper;

import com.project.travelAgency.model.dto.TourDto;
import com.project.travelAgency.model.entity.Tour;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

//преобразование Tour в TourDto и обратно
@Mapper
public interface TourMapper {

    TourMapper MAPPER = Mappers.getMapper(TourMapper.class);

    Tour toTour(TourDto tourDto);

    @InheritInverseConfiguration
    TourDto fromTour(Tour tour);

    List<Tour> toTourList(List<TourDto> tourDtos);

    List<TourDto> fromTourList(List<Tour> tours);
}
