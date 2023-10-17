package com.example.springrestmavenjava.data.modelo.mappers;


import com.example.springrestmavenjava.data.modelo.UserEntity;
import com.example.springrestmavenjava.domain.modelo.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private MotoMapper motoMapper;

    public UserMapper(MotoMapper motoMapper) {
        this.motoMapper = motoMapper;
    }

    public User toUser(UserEntity user) {
        return new User(user.getId(),
                user.getUsername(),
                user.getPassword(),
                null);
    }

    public User toUserWithMotos(UserEntity user) {
        return new User(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMotos().stream()
                        .map(motoMapper::toMoto)
                        .toList());
    }


    public UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity(user.id(),
                user.username(),
                user.password(),
                null);
        if (user.motos() != null) {
            userEntity.setMotos(
                    user.motos().stream()
                            .map(moto -> motoMapper.toMotoEntity(moto, userEntity))
                            .toList());
        }

        return userEntity;
    }

}

