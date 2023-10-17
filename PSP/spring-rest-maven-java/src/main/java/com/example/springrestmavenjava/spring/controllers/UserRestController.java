package com.example.springrestmavenjava.spring.controllers;

import com.example.springrestmavenjava.domain.modelo.User;
import com.example.springrestmavenjava.domain.modelo.UserWithoutMotoDTO;
import com.example.springrestmavenjava.domain.usecases.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {


    private UserServices userService;


    public UserRestController(UserServices userServices) {
        this.userService = userServices;
    }

    @GetMapping
    public List<UserWithoutMotoDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}

