package com.example.springrestmavenjava.data.modelo.mappers;


import com.example.springrestmavenjava.data.modelo.UserEntity;
import com.example.springrestmavenjava.domain.modelo.User;
import com.example.springrestmavenjava.domain.modelo.UserWithoutMotoDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserWithoutMotoDTOMapper {




    public UserWithoutMotoDTO toUser(UserEntity user) {
        return new UserWithoutMotoDTO(user.getId(),
                user.getUsername(),
                user.getPassword());
    }


    public UserEntity toUserEntity(UserWithoutMotoDTO user) {
        UserEntity userEntity = new UserEntity(user.id(),
                user.username(),
                user.password(),
                null);

        return userEntity;
    }

}
