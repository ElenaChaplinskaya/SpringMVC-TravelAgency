package com.project.travelAgency.controller;

import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.model.entity.Country;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.service.impl.TourServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {

    private final TourServiceImpl tourService;
    private final TourRepository tourRepository;

    @GetMapping
    public String list(Model model) {
        List<TourDto> list = tourService.getAll();
        model.addAttribute("tours", list);
        return "tours";
    }

    @GetMapping("/{id}/cart")
    public String addCart(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/tours";
        }
        tourService.addToUserCart(id, principal.getName());
        return "redirect:/tours";
    }

    @GetMapping("/showCreateTour")
    public String showCreateTour(Model model) {

        model.addAttribute("country", new CountryDto());
        model.addAttribute("tour", new TourDto());
        return "createTour";
    }

    @PostMapping("/createTour")
    public String createTour(@ModelAttribute TourDto tourDto,
                             @ModelAttribute CountryDto countryDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "tours";
        }
        tourService.save(tourDto, countryDto);
        List<TourDto> list = tourService.getAll();
        model.addAttribute("tours", list);
        return "tours";
    }

    @GetMapping("/{id}/deleteTour")
    public String deleteTour(@PathVariable Long id, Model model) {

        tourRepository.deleteById(id);
        List<TourDto> list = tourService.getAll();
        List<TourDto> newList = tourService.getAll();
        model.addAttribute("tours", newList);
        return "tours";
    }

    @GetMapping("/{id}")
    public String editTour(@PathVariable Long id, Model model) {

        model.addAttribute("tour", tourService.getById(id));
        return "editTour";

    }

    @PostMapping("/editTour")
    public String editTour(@ModelAttribute Tour tour,  @ModelAttribute Country country, Model model) {

        model.addAttribute("country", new Country());
        tourRepository.save(tour);
        List<TourDto> list = tourService.getAll();
        model.addAttribute("tours", list);
        return "tours";
    }
}