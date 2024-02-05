package com.example.demo.ui.controllers;

import com.example.demo.data.modelo.UserEntity;
import com.example.demo.domain.modelo.User;
import com.example.demo.domain.modelo.UserEnteroDTO;
import com.example.demo.domain.modelo.UserVisitasDTO;
import com.example.demo.domain.servicios.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRest {


    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserVisitasDTO getUser(@PathVariable Long id)
    {
        return userService.getById(id);
    }
    @GetMapping("/all/{id}")
    public UserEnteroDTO getUserEntero(@PathVariable Long id)
    {
        return userService.getEnteroById(id);
    }

    @PostMapping()
    public User saveUserEntero(@RequestBody User user)
    {
        return userService.save(user);
    }


}
