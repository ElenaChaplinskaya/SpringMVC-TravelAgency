package com.project.tvavelAgency.controller;

import lombok.AllArgsConstructor;
import com.project.tvavelAgency.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.tvavelAgency.service.UserService;

import javax.xml.bind.ValidationException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDto userDto, Model model) throws ValidationException {
        if (userService.save(userDto)) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userDto);
        }
        return "user";
    }
}
