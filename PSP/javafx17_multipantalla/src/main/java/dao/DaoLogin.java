package dao;

import common.ErrorApp;
import common.ResultMio;
import domain.modelo.Usuario;
import io.vavr.control.Either;

public interface DaoLogin {

    Either<ErrorApp, ResultMio<Boolean>> doLogin(Usuario user);
}
