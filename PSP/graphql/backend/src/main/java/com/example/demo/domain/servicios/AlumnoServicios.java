package com.example.demo.domain.servicios;

import com.example.demo.data.modelo.Alumno;
import com.example.demo.data.repositories.AlumnoRepository;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AlumnoServicios {



    public AlumnoServicios(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }


    private final AlumnoRepository alumnoRepository;



    public List<Alumno> findAll() {

        log.info("AlumnoServicios.findAll()");
        return alumnoRepository.findAll();
    }

    public Alumno findAlumno(int id) {
        log.info("AlumnoServicios.findAlumno()");

        return alumnoRepository.findAlumno(id);
    }

    public int insertAlumno(Alumno alumno) {
        log.info("AlumnoServicios.insertAlumno()");
        return alumnoRepository.insertAlumno(alumno);
    }

    public int deleteAlumno(int id) {
        log.info("AlumnoServicios.deleteAlumno()");
        return alumnoRepository.deleteAlumno(id);
    }

    public int updateAlumno(Alumno alumno) {

//        Validator validator;
//        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
//            validator = factory.getValidator();
//        }
//        Set<ConstraintViolation<Alumno>> violations = validator.validate(alumno);
//
//        violations.forEach(violation -> {
//            log.error(violation.getMessage());
//        });
        log.info("AlumnoServicios.updateAlumno()");
        return alumnoRepository.updateAlumno(alumno);
    }



}
