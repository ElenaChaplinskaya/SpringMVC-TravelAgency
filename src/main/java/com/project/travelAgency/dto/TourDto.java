package com.project.travelAgency.dto;


import com.project.travelAgency.model.entity.Status;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.TypeOfTour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {

    private Long id;
    private TypeOfTour typeOfTour;
    private CountryDto countryDto;
    private int days;
    private BigDecimal price;
    private Status status;


    public TourDto(Tour tour) {
        this.id = tour.getId();
        this.typeOfTour = tour.getTypeOfTour();
        this.countryDto = new CountryDto(tour.getCountry());
        this.days = tour.getDays();
        this.price = tour.getPrice();
        this.status = tour.getStatus();
    }
}
