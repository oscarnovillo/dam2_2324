package com.example.demo.domain.servicios;


import com.example.demo.data.modelo.CiudadEntity;
import com.example.demo.data.repositories.CiudadRepository;
import com.example.demo.domain.modelo.Ciudad;
import com.example.demo.domain.modelo.Poi;
import com.example.demo.domain.modelo.mappers.CiudadEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CiudadService {

    private final CiudadRepository ciudadRepository;
    private final CiudadEntityMapper ciudadEntityMapper;


    public List<Ciudad> getCiudades() {
        return ciudadRepository.findAll().stream()
                .map(ciudadEntityMapper::toCiudad)
                .toList();
    }

    public Ciudad addCiudad(String nombre) {
        CiudadEntity ciudadEntity = ciudadRepository.save(
                new CiudadEntity(0L, nombre,null));

        return ciudadEntityMapper.toCiudad(ciudadEntity);
    }

}
