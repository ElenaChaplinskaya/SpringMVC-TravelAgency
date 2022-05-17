package com.project.travelAgency.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name="type_of_tour_id")
    private TypeOfTour typeOfTour;
    @OneToOne
    @JoinColumn(name="country_id")
    private Country country;
    @OneToOne
    @JoinColumn(name="during_id")
    private During during;
    @Column
    private BigDecimal price;

}
