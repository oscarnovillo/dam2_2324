package dao.impl;

import common.Error2;
import common.ErrorApp;
import common.ResultMio;
import common.Success;
import common.config.Configuracion;
import dao.DaoLogin;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DaoLoginImpl implements DaoLogin {


    private Configuracion configuracion;

    @Inject
    public DaoLoginImpl(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public Either<ErrorApp, ResultMio<Boolean>> doLogin(Usuario user) {

        // buscar usuario
        if (user.nombre().equals("admin") || user.nombre().equals("user"))
            return Either.right(new Success<>(true));
        return Either.left(new Error2("error"));
    }
}
