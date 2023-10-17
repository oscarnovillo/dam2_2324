package com.example.springrestmavenjava.data.modelo.mappers;

import com.example.springrestmavenjava.data.modelo.MotoEntity;
import com.example.springrestmavenjava.data.modelo.UserEntity;
import com.example.springrestmavenjava.domain.modelo.Moto;
import com.example.springrestmavenjava.domain.modelo.User;
import org.springframework.stereotype.Component;

@Component
public class MotoMapper {

    public Moto toMoto(MotoEntity moto) {
        return new Moto(moto.getId(), moto.getNombre());
    }

    public MotoEntity toMotoEntity(Moto moto,UserEntity user) {
        return new MotoEntity(moto.id(), moto.nombre(),user);
    }


}
