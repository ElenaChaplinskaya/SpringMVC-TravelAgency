package com.project.travelAgency.dto;

import com.project.travelAgency.model.entity.TypeOfTour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfTourDto {

    private Integer id;
    private String title;

    public TypeOfTourDto(TypeOfTour typeOfTour) {
        this.id =typeOfTour.getId();
        this.title = typeOfTour.getTitle();
    }
}
