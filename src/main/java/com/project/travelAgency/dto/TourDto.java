package com.project.travelAgency.dto;


import com.project.travelAgency.model.entity.Country;
import com.project.travelAgency.model.entity.Status;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.TypeOfTour;
import lombok.*;
import org.springframework.integration.annotation.Default;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {

    private Long id;
    private TypeOfTourDto typeOfTourDto;
    private CountryDto countryDto;
    private int days;
    private BigDecimal price;
    private Status status;


    public TourDto(Tour tour) {
        this.id = tour.getId();
        this.typeOfTourDto = new TypeOfTourDto(tour.getTypeOfTour());
        this.countryDto = new CountryDto(tour.getCountry());
        this.days = tour.getDays();
        this.price = tour.getPrice();
        this.status = tour.getStatus();
    }
}
