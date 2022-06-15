package com.project.travelAgency.model.mapper;

import com.project.travelAgency.dto.TypeOfTourDto;
import com.project.travelAgency.model.entity.TypeOfTour;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeOfTourMapper {

    TypeOfTourMapper MAPPER = Mappers.getMapper(TypeOfTourMapper.class);

    TypeOfTour toTypeOfTour(TypeOfTourDto typeOfTourDto);

    @InheritInverseConfiguration
    TypeOfTourDto fromTypeOfTour(TypeOfTour typeOfTour); //получает Tour, возвращает TourDto


}
