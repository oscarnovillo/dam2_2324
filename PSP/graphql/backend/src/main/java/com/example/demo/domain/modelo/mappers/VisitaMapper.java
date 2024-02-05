package com.example.demo.domain.modelo.mappers;

import com.example.demo.data.modelo.VisitaEntity;
import com.example.demo.domain.modelo.Visita;
import com.example.demo.domain.modelo.VisitaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {

    Visita toVisita(VisitaEntity visitaEntity);
    VisitaEntity toVisitaEntity(Visita visita);

    VisitaDTO toVisitaDTO(VisitaEntity visitaEntity);
    VisitaDTO toVisitaDTO(Visita visita);
}
