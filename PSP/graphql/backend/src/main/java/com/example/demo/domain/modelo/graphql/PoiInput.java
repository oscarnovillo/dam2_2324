package com.example.demo.domain.modelo.graphql;

import com.example.demo.domain.modelo.Ciudad;

public record PoiInput(
        String nombre,
        Float latitud,
        Float longitud,
        String tipo,
        Ciudad ciudad

) {
}
