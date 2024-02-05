package com.example.demo.ui.controllers;


import com.example.demo.data.modelo.Alumno;
import com.example.demo.domain.servicios.AlumnoServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ControllerDemo {

    Logger log = LoggerFactory.getLogger(ControllerDemo.class);

    private final AlumnoServicios alumnoServicios;

    public ControllerDemo(AlumnoServicios alumnoServicios) {
        this.alumnoServicios = alumnoServicios;
    }

    @GetMapping("/demo")
    @ResponseBody
    public String index() {
        log.info("index");

        return "<html ><body><h1>index</h1></body></html>";
    }


    @GetMapping("/prueba")

    public String prueba(Model model) {

        List<Alumno> alumnos = alumnoServicios.findAll();
        model.addAttribute("alumnos", alumnos);
        return "template";
    }
}
