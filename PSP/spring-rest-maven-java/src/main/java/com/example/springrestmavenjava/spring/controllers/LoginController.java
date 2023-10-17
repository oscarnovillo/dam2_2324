package com.example.springrestmavenjava.spring.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@Log4j2
public class LoginController {
    private final Key key;

    private final AuthenticationManager authenticationManager;

    public LoginController(@Qualifier("JWT") Key key, AuthenticationManager authenticationManager) {
        this.key = key;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "user",required = false) String user, @RequestParam(name = "password",required = false) String password) {
        // get parameter name and send it to thymeleaf
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));

        return Jwts.builder()
                .setSubject("parael servidor")
                .setIssuer("YO")
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("user", auth.getName())
                .claim("group", auth.getAuthorities())
                .claim("jj", "jajaj")
                .signWith(key).compact();
    }

    @GetMapping("/verify")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String verify(HttpServletRequest request,Authentication auth, @RequestParam(name = "token",required = false) String token) {
        // get parameter name and send it to thymeleaf
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        }
        catch (Exception e){
            log.error("Error al verificar el token",e);
        }
        auth.getAuthorities().forEach(System.out::println);
        return request.isUserInRole("ROLE_ADMIN")?"Es admin":"No es admin";
    }

}
