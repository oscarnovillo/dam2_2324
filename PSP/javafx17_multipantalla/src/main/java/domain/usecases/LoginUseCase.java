package domain.usecases;

import common.ErrorApp;
import common.ResultMio;
import domain.modelo.Usuario;
import io.vavr.control.Either;

public interface LoginUseCase {
    Either<ErrorApp, ResultMio<Boolean>> doLogin(Usuario usuario);
}
