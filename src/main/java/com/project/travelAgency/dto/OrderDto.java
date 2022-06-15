package com.project.travelAgency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private  int amountTours;
    private Double sum;
    private List<OrderDetailDto> orderDetails = new ArrayList<>();

    public void aggregate(){
        this.amountTours = orderDetails.size();
        this.sum = orderDetails.stream()
                .map(OrderDetailDto::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
