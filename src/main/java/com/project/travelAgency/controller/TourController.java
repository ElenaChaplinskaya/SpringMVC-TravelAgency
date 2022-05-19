package com.project.travelAgency.controller;

import com.project.travelAgency.model.dto.TourDto;
import com.project.travelAgency.service.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourServiceImpl tourService;

    @GetMapping
    public String list(Model model) {
        List<TourDto> list = tourService.getAll();
        model.addAttribute("tours", list);
        return "tours";
    }

    //добавляем туры в корзину
    @GetMapping("/{id}/cart")
    public String addCart(@PathVariable Integer id, Principal principal) {
        if (principal == null) {
            return "redirect:/tours";
        }
        tourService.addToUserCart(Long.valueOf(id), principal.getName());
        return "redirect:/tours";
    }
}
