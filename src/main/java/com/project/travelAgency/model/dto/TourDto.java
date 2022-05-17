package com.project.travelAgency.model.dto;

import com.project.travelAgency.model.entity.Country;
import com.project.travelAgency.model.entity.During;
import com.project.travelAgency.model.entity.TypeOfTour;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TourDto {

    private Integer id;
    private TypeOfTour typeOfTour;
    private Country country;
    private During during;
    private BigDecimal price;
}
