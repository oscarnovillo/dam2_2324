package com.example.demo.ui.graphql;

import com.example.demo.domain.modelo.*;
import com.example.demo.domain.modelo.graphql.PoiInput;
import com.example.demo.domain.servicios.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PoisController {

    private final PoiService poiService;

    @SchemaMapping(typeName = "Ciudad", field = "pois")
    public List<Poi> getPois(Ciudad ciudad) {
        return poiService.getPoisDeCiudad(ciudad);
    }

    @SchemaMapping(typeName = "Visitas", field = "pois")
    public List<Poi> getPoisVisitas(Visita visita) {
        return poiService.getPoisDeVisita(visita);
    }


    @MutationMapping
    public Poi addPoi(@Argument PoiInput poi) {
        return poiService.addPoi(poi);
    }

    @QueryMapping
    public List<Poi> getPois() {
        return poiService.getPois();
    }


}
