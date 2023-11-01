package domain.servicios;

import dao.DaoErrores;
import dao.modelo.Usuario;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosBuenosUsuarios {

    private DaoErrores dao;

    @Inject
    public ServiciosBuenosUsuarios(DaoErrores dao) {
        this.dao = dao;
    }

    public List<Usuario> dameTodos() {
        return dao.dameTodos();
    }

    public Usuario dameUsuario(String id) {

        return dao.dameUsuario(id);
    }
}
