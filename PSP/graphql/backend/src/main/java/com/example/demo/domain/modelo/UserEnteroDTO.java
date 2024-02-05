package com.example.demo.domain.modelo;

import java.util.List;

public record UserEnteroDTO(Long id, String nombre,
                            String password,
                            List<Rol> roles,
                            List<Visita> visitas) {
}
