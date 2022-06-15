package com.project.travelAgency.model.mapper;

import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.model.entity.Tour;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

//преобразование Tour в TourDto и обратно
@Mapper(uses = {TypeOfTourMapper.class})
public interface TourMapper {

    TourMapper MAPPER = Mappers.getMapper(TourMapper.class);


    Tour toTour(TourDto tourDto); //получает TourDto, возвращает Tour

    @InheritInverseConfiguration
    TourDto fromTour(Tour tour); //получает Tour, возвращает TourDto

    List<Tour> toTourList(List<TourDto> tourDtos);

    List<TourDto> fromTourList(List<Tour> tours);
}

