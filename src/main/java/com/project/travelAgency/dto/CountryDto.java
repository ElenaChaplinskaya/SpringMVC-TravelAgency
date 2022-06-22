package com.project.travelAgency.dto;

import com.project.travelAgency.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {
    private Integer id;
    private String country;
    private String city;

    public CountryDto(Country country) {
        this.country = country.getCountry();
        this.city = country.getCity();
    }
}
