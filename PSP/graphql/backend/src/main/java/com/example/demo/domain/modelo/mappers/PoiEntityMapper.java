package com.example.demo.domain.modelo.mappers;

import com.example.demo.data.modelo.PoiEntity;
import com.example.demo.domain.modelo.Poi;
import com.example.demo.domain.modelo.graphql.PoiInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoiEntityMapper {

    PoiEntity toPoiEntity(Poi poi);
    Poi toPoi(PoiEntity poiEntity);
    Poi toPoi(PoiInput poiInput);
}
