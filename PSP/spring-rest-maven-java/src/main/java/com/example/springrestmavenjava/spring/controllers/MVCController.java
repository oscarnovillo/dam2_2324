package com.example.springrestmavenjava.spring.controllers;

import com.example.springrestmavenjava.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MVCController {


    @GetMapping("/")
    public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        // get parameter name and send it to thymeleaf

        model.addAttribute("name", name);
        if (name.equals("World")) {
            throw new NotFoundException("No se ha encontrado el usuario");
        }
        return "index";
    }


}
