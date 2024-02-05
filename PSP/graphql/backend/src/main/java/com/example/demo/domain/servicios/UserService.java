package com.example.demo.domain.servicios;

import com.example.demo.data.modelo.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.domain.modelo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAllWithPermisos().stream().map(
                userEntity ->
                {
                    List<Rol> roles = userEntity.getRoles().stream().map(
                            rolEntity ->
                                    new Rol(rolEntity.getId(),
                                            rolEntity.getRol()
                                    )
                    ).toList();
                    return new User(userEntity.getId(),
                            userEntity.getName(),
                            "",
                            roles
                    );
                }
        ).toList();
//                .stream()
//                .peek(UserEntity::getVisitas).toList();
    }


    public UserVisitasDTO getById(Long id) {
        return userRepository.findWithVisitasById(id).map(
                userEntity ->
                {
                    List<Visita> visitas = userEntity.getVisitas().stream().map(
                            visitaEntity ->
                                    new Visita(
                                            visitaEntity.getId(),
                                            visitaEntity.getFechaInicial(),
                                            visitaEntity.getFechaFinal(),
                                            visitaEntity.getPois().stream().map(
                                                    poiEntity ->
                                                            new Poi(
                                                                    poiEntity.getId(),
                                                                    poiEntity.getLatitud(),
                                                                    poiEntity.getLongitud(),
                                                                    poiEntity.getNombre(),
                                                                    poiEntity.getTipo(),
                                                                    new Ciudad(
                                                                            poiEntity.getCiudad().getId(),
                                                                            poiEntity.getCiudad().getNombre()
                                                                    )
                                                            )
                                            ).toList()
                                    )
                    ).toList();
                    return new UserVisitasDTO(userEntity.getId(),
                            userEntity.getName(),
                            visitas
                    );
                }
        ).orElseThrow();
    }

    public UserEnteroDTO getEnteroById(Long id) {
        return userRepository.findEnteroById(id).map(
                userEntity ->
                {
                    List<Visita> visitas = userEntity.getVisitas().stream().map(
                            visitaEntity ->
                                    new Visita(
                                            visitaEntity.getId(),
                                            visitaEntity.getFechaInicial(),
                                            visitaEntity.getFechaFinal(),
                                            visitaEntity.getPois().stream().map(
                                                    poiEntity ->
                                                            new Poi(
                                                                    poiEntity.getId(),
                                                                    poiEntity.getLatitud(),
                                                                    poiEntity.getLongitud(),
                                                                    poiEntity.getNombre(),
                                                                    poiEntity.getTipo(),
                                                                    new Ciudad(
                                                                            poiEntity.getCiudad().getId(),
                                                                            poiEntity.getCiudad().getNombre()
                                                                    )
                                                            )
                                            ).toList()
                                    )
                    ).toList();
                    List<Rol> roles = userEntity.getRoles().stream().map(
                            rolEntity ->
                                    new Rol(rolEntity.getId(),
                                            rolEntity.getRol()
                                    )
                    ).toList();
                    return new UserEnteroDTO(userEntity.getId(),
                            userEntity.getName(),
                            "",
                            roles,
                            visitas
                    );
                }
        ).orElseThrow();
    }

    public User save(User user) {

        UserEntity userEntity = userRepository.save(new UserEntity(
                0L,
                user.nombre(),
                user.password(),
                null,
                null

        ));

        return new User(userEntity.getId(),
                userEntity.getName(),
                userEntity.getPassword(),
                null);

    }
}
