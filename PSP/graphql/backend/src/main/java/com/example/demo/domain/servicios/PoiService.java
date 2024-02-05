package com.example.demo.domain.servicios;

import com.example.demo.data.modelo.PoiEntity;
import com.example.demo.data.repositories.PoiRepository;
import com.example.demo.domain.modelo.*;
import com.example.demo.domain.modelo.graphql.PoiInput;
import com.example.demo.domain.modelo.mappers.PoiEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PoiService {

    private final PoiRepository poiRepository;
    private final PoiEntityMapper poiEntityMapper;


    public List<Poi> getPoisDeCiudad(Ciudad ciudad) {
        return poiRepository.findAllByCiudad_id(ciudad.id())
                .stream().map(poiEntityMapper::toPoi)
                .toList();
    }

    public List<Poi> getPoisDeVisita(Visita visita) {
        return poiRepository.findAllByVisitas_Id(visita.id())
                .stream().map(poiEntityMapper::toPoi)
                .toList();
    }

    public Poi addPoi(PoiInput poiInput) {
        Poi poi = poiEntityMapper.toPoi(poiInput);
        PoiEntity poiEntity = poiRepository.save(
                poiEntityMapper.toPoiEntity(poi));

        return poiEntityMapper.toPoi(poiEntity);
    }

    public List<Poi> getPois() {
        return poiRepository.findAllWithCiudad().stream()
                .map(poiEntityMapper::toPoi)
                .toList();
    }
}
