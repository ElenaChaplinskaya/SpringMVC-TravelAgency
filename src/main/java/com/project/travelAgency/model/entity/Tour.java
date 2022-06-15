package com.project.travelAgency.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="type_of_tour_id")
    private TypeOfTour typeOfTour;
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="country_id")
    private Country country;
    @Column
    private int days;
    @Column
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany(mappedBy = "tours")
    private List<Order> orders;

}
