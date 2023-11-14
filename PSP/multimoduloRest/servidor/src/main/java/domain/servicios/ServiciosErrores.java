package domain.servicios;

import dao.DaoErrores;

import domain.modelo.Usuario;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosErrores {

    private DaoErrores dao;

    @Inject
    public ServiciosErrores(DaoErrores dao) {
        this.dao = dao;
    }

    public List<Usuario> dameTodos() {
        return dao.dameTodos();
    }

    public Usuario dameUsuario(String id) {

        return dao.dameUsuario(id);
    }
}
