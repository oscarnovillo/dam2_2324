package com.example.demo.ui.controllers;


import com.example.demo.data.modelo.Alumno;
import com.example.demo.domain.servicios.AlumnoServicios;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestAlumnos {



    private final AlumnoServicios alumnoServicios;

    public RestAlumnos(AlumnoServicios alumnoServicios) {
        this.alumnoServicios = alumnoServicios;
    }

    @GetMapping("/api/alumnos")
    @Secured("ROLE_ADMIN")
    public List<Alumno> index() {

        return  alumnoServicios.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/alumnos/{id}")
    public Alumno getAlumno(@PathVariable int id) {

        return  alumnoServicios.findAlumno(id);
    }

    @PostMapping("/api/alumnos")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Alumno indexPost(@Valid @RequestBody Alumno alumno) {
        alumnoServicios.insertAlumno(alumno);
        return alumno;
    }


    @DeleteMapping("/api/alumnos/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAlumno(@PathVariable int id) {
        alumnoServicios.deleteAlumno(id);
    }

    @PutMapping("/api/alumnos")
    public Alumno updateAlumno(@RequestBody Alumno alumno) {
        alumnoServicios.updateAlumno(alumno);
        return alumno;
    }
}
