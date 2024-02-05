package com.example.demo.domain.modelo;

import java.util.List;

public record User(Long id, String nombre,
                   String password,
                   List<Rol> roles) {
}
