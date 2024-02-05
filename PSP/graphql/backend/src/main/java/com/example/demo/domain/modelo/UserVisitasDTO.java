package com.example.demo.domain.modelo;

import java.util.List;

public record UserVisitasDTO(
        Long id,
        String nombre,
        List<Visita> visitas
) {
}
