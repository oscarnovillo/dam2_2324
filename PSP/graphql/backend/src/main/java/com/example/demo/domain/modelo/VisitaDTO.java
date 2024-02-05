package com.example.demo.domain.modelo;

import java.time.LocalDate;

public record VisitaDTO(Long id,
                        LocalDate fechaInicial,
                        LocalDate fechaFinal

                        ) {
}
