package com.example.springrestmavenjava.util;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {


    public boolean validate(String token) {
        return true;
    }

    public String getUsername(String token) {
        return "user";
    }
}
