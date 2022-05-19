package com.project.travelAgency.controller;

import com.project.travelAgency.model.dto.CartDto;
import com.project.travelAgency.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String forCart(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("cart", new CartDto());
        } else {
            CartDto cartDto = cartService.getCartByUser(principal.getName());
            model.addAttribute("cart", cartDto);
        }
        return "cart";
    }

}
