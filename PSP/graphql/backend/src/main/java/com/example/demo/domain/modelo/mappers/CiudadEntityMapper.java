package com.example.demo.domain.modelo.mappers;

import com.example.demo.data.modelo.CiudadEntity;
import com.example.demo.domain.modelo.Ciudad;
import com.example.demo.domain.modelo.graphql.CiudadInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CiudadEntityMapper {

    CiudadEntity toCiudadEntity(Ciudad ciudad);
    Ciudad toCiudad(CiudadEntity ciudadEntity);
    Ciudad toCiudad(CiudadInput ciudadInput);
}
