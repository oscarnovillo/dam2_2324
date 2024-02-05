package com.example.demo;

import com.example.demo.data.modelo.Alumno;
import com.example.demo.data.modelo.UserEntity;
import com.example.demo.data.repositories.AlumnoRepository;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.domain.servicios.AlumnoServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(
            AlumnoServicios alumnoServicios,
            PasswordEncoder encoder,
            UserRepository userRepository
            ) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);
            UserEntity user = UserEntity.builder()
                    .id(11L)
                    .name("Pedro")
                    .password(encoder.encode("1234"))
                    .build();
            userRepository.save(user);

            user.setName("Juan");
            userRepository.save(user);
//                      userRepository.findAll().forEach(userEntity -> {
//                logger.info("UserEntity: {}", userEntity);
//            });
//            userRepository.findById(UUID.fromString("c69be946-a960-4d42-8e05-91ec227abb3d")).ifPresent(userEntity -> {
//                userEntity.setName("Juan");
//                logger.info("UserEntity encontrado: {}", userEntity);
//                userRepository.save(userEntity);
//            });
            //userRepository.save(user);

            if (alumnoServicios.findAll().isEmpty()) {
                for (int i = 0; i < 1000; i++) {
                    alumnoServicios.insertAlumno(new Alumno(0, "Pedro" + i));
                }
            }

        };
    }

}
