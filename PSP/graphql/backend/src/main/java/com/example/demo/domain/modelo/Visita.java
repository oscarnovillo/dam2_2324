package com.example.demo.domain.modelo;

import java.time.LocalDate;
import java.util.List;

public record Visita(Long id,
                     LocalDate fechaInicial,
                     LocalDate fechaFinal,
                     List<Poi> pois) {
}
