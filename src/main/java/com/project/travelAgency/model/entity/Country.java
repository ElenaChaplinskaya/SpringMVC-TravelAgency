package com.project.travelAgency.model.entity;

import com.project.travelAgency.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String country;
    @Column
    private String city;

    public Country(CountryDto countryDto) {
        this.country = countryDto.getCountry();
        this.city = countryDto.getCity();

    }
}
