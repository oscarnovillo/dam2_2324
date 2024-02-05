package com.example.demo.ui.graphql;

import com.example.demo.domain.modelo.Visita;
import com.example.demo.domain.modelo.VisitaDTO;
import com.example.demo.domain.servicios.VisitasService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VisitasController {


    private final VisitasService visitasService;

    @QueryMapping(name  = "getVisitas")
    public List<Visita> getVisitas(Principal principal){
        return visitasService.getAllByUserNameWithAll(principal.getName());
    }
}
