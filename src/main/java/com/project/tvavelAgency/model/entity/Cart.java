package com.project.tvavelAgency.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @JoinTable(name ="cart_tour",
        joinColumns = @JoinColumn(name="cart_id"),
        inverseJoinColumns = @JoinColumn(name="tour_id"))
    private List<Tour> tours;
}
