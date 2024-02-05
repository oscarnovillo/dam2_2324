package com.example.demo.domain.modelo;

public record Poi(Long id,
                  Double latitud,
                  Double longitud,
                  String nombre,
                  String tipo,
                  Ciudad ciudad
             ) {
}
