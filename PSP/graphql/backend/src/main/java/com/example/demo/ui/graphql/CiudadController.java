package com.example.demo.ui.graphql;

import com.example.demo.domain.modelo.Ciudad;
import com.example.demo.domain.modelo.Poi;
import com.example.demo.domain.servicios.CiudadService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CiudadController {

    private final CiudadService ciudadService;

    @QueryMapping
    @PreAuthorize("hasRole('USER')")
    public List<Ciudad> getCiudades() {
        return ciudadService.getCiudades();
    }


    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Ciudad addCiudad(@Argument String nombre) {
        return ciudadService.addCiudad(nombre);
    }

}
