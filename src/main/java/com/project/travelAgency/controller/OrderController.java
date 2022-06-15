package com.project.travelAgency.controller;


import com.project.travelAgency.dto.OrderDto;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.service.OrderService;
import com.project.travelAgency.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final TourService tourService;


    @GetMapping("/order")
    public String forOrder(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("order", new OrderDto());
        } else {
            OrderDto orderDto = orderService.getOrderByUser(principal.getName());
            model.addAttribute("order", orderDto);
        }
        return "order";
    }
//
//    @GetMapping("/deleteTour")
//    private String deleteTourFromOrder(@RequestParam (name = "tourId") long tourId, @RequestParam (name = "orderId") Long orderId) {
//
//        Tour tempTour=tourService.findById(tourId);
//        Order tempOrder = orderService.getById(orderId);
//        tempOrder.removeTour(tempTour);
//        orderService.save(tempOrder);
//
//
//
//      //  cartService.deleteTourByUser(principal.getName(), 1L);
//        return "redirect:/order?tourId="+tourId;
//    }

}
