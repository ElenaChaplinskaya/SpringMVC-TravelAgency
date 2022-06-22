package com.project.travelAgency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error") //если не верный логин, пароль, переходит на 404-page
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
