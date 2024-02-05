package com.example.demo.data.repositories;

import com.example.demo.data.modelo.Alumno;
import com.example.demo.ui.errores.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AlumnoRepository {

    Logger logger = LoggerFactory.getLogger(AlumnoRepository.class);
    private final JdbcClient jdbcClient;

    public AlumnoRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Alumno> findAll() {
        logger.debug("AlumnoRepository.findAll()");
        return jdbcClient.sql("select * from alumnos")
                .query(Alumno.class).list();
    }

    public Alumno findAlumno(int id) {
        Alumno a = null;
        try {
            a = jdbcClient.sql("select * from alumnos where id = ?")
                    .param(1, id)
                    .query(Alumno.class).single();

        }
        catch (EmptyResultDataAccessException e){
            // dejar un log
            throw new NotFoundException("Alumno no encontrado");
        }
        return a;
    }

    public int insertAlumno(Alumno alumno) {
        return jdbcClient.sql("insert into alumnos (name) values (?)")
                .param(1,alumno.getName())
                .update();
    }

    public int deleteAlumno(int id) {
        return jdbcClient.sql("delete from alumnos where id = ?")
                .param(1,id)
                .update();
    }

    public int updateAlumno(Alumno alumno) {
        return jdbcClient.sql("update alumnos set name = :name where id = :id")
                .param("id",alumno.getId())
                .param("name",alumno.getName())
                .update();
    }
}
