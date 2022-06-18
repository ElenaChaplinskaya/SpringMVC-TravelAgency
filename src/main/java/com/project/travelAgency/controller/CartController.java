package com.project.travelAgency.controller;


import com.project.travelAgency.dto.CartDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.repository.CartRepository;
import com.project.travelAgency.service.CartService;
import com.project.travelAgency.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final TourService tourService;
    private final CartRepository cartRepository;


    @GetMapping("/cart")
    public String forOrder(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("cart", new CartDto());
        } else {
            CartDto cardDto = cartService.getCartByUser(principal.getName());
            model.addAttribute("cart", cardDto);
        }
        return "cart";
    }
//    @GetMapping("/cart/deleteTourFromCart")
//    private String deleteTourFromCart(Long tourId) {
//
//        Tour tempTour=tourService.getById(tourId);
////        Order tempOrder = orderService.getById(orderId);
////        tempOrder.removeTour(tempTour);
////        orderService.save(tempOrder);
//
//
////
////      //  cartService.deleteTourByUser(principal.getName(), 1L);
//        return "redirect:/order?tourId="+tourId;
//    }

}
