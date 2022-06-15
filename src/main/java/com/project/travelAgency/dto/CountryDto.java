package com.project.travelAgency.dto;

import com.project.travelAgency.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.integration.annotation.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Default
public class CountryDto {

    private String country;
    private String city;

    public CountryDto(Country country) {
        this.country = country.getCountry();
        this.city = country.getCity();
    }
}
