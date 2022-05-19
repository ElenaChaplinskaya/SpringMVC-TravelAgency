package com.project.travelAgency.model.dto;

import com.project.travelAgency.model.entity.Country;
import com.project.travelAgency.model.entity.During;
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
public class CartDetailDto {
    private TypeOfTour typeOfTour;
    private Country country;
    private During during;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;

    public CartDetailDto(Tour tour) {
        this.typeOfTour = tour.getTypeOfTour();
        this.country = tour.getCountry();
        this.during = tour.getDuring();
        this.price = tour.getPrice();
        this.amount = new BigDecimal(1.0);
        this.sum = Double.valueOf(tour.getPrice().toString());
    }
}
