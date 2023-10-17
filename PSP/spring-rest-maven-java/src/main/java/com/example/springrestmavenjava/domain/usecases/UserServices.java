package com.example.springrestmavenjava.domain.usecases;

import com.example.springrestmavenjava.data.UserEntityRepository;
import com.example.springrestmavenjava.data.modelo.UserEntity;
import com.example.springrestmavenjava.domain.exceptions.NotFoundException;
import com.example.springrestmavenjava.domain.modelo.User;
import com.example.springrestmavenjava.domain.modelo.UserWithoutMotoDTO;
import com.example.springrestmavenjava.data.modelo.mappers.UserMapper;
import com.example.springrestmavenjava.data.modelo.mappers.UserWithoutMotoDTOMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Log4j2
public class UserServices {

    private final UserEntityRepository userEntityRepository;

    private final UserMapper userMapper;
    private final UserWithoutMotoDTOMapper userWithoutMotoDTOMapper;


    public UserServices(UserEntityRepository userEntityRepository, UserMapper userMapper, UserWithoutMotoDTOMapper userWithoutMotoDTOMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
        this.userWithoutMotoDTOMapper = userWithoutMotoDTOMapper;
    }


    @Transactional
    public List<UserWithoutMotoDTO> getAllUsers() {
        return userEntityRepository.findAll()
                .stream()
                .map(userWithoutMotoDTOMapper::toUser)
                .toList();
    }

    @Transactional
    public User getUserById(Long id) {
        return userEntityRepository.findByIdWithMotos(id)
                .map(userMapper::toUserWithMotos)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

    }


    @Transactional
    public User createUser(User user) {
        UserEntity uE = userMapper.toUserEntity(user);
        uE = userEntityRepository.save(uE);
        log.info(uE.getId());
        User nuevo = userMapper
                .toUserWithMotos(uE);
        log.info("Usuario creado: " + nuevo.id());
        return nuevo;
    }

    public User updateUser(Long id, User user) {

        int numeroFilasUpdate = userEntityRepository.updateUsernameAndPasswordById(user.username(), user.password(), id.intValue());
        if (numeroFilasUpdate == 0) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return user;
    }


    @Secured("ADMIN")
    public void deleteUser(Long id) {

        try {
            userEntityRepository.deleteById(id.intValue());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Usuario no encontrado");
        }
//        catch (DataIntegrityViolationException e) {
//            throw new BadRequestException("No se puede eliminar el usuario porque tiene motos asociadas");
//        }


    }


    @Transactional
    public void deleteUserWithMotos(Long id) {

        userEntityRepository.deleteById(id.intValue());

    }

}
