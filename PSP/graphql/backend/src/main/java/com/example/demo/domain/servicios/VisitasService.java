package com.example.demo.domain.servicios;

import com.example.demo.data.repositories.VisitasRepository;
import com.example.demo.domain.modelo.Visita;
import com.example.demo.domain.modelo.VisitaDTO;
import com.example.demo.domain.modelo.mappers.VisitaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitasService {


    private final VisitasRepository visitasRepository;
    private final VisitaMapper visitasMapper;



    public List<VisitaDTO> getAllByUserName(String userName)
    {
        return visitasRepository.findAllByUser_Name(userName).stream()
                .map(visitasMapper::toVisitaDTO)
                .toList();
    }


    public List<Visita> getAllByUserNameWithAll(String name) {
        return visitasRepository.findAllByUser_NameWithAll(name).stream()
                .map(visitasMapper::toVisita)
                .toList();
    }
}
