package dao;

import domain.modelo.Usuario;
import domain.modelo.errores.BaseDatosCaidaException;
import domain.modelo.errores.NotFoundException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DaoErrores {


    public static List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> dameTodos() {

       // usuarios.add(new Usuario("99","nombre","pass", LocalDateTime.now()));

        Random r = new Random();

        if (r.nextBoolean())
            throw new BaseDatosCaidaException("Base Datos Caida");

        return usuarios;
    }

    public Usuario dameUsuario(String id) {
        Random r = new Random();

        if (r.nextBoolean())
            throw new NotFoundException("Usuario No encontrado");

        return new Usuario(id,"un usuario","pass", LocalDateTime.now());
    }
}
