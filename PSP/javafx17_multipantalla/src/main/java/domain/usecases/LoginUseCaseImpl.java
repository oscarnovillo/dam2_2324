package domain.usecases;

import common.Error3;
import common.ErrorApp;
import common.ResultMio;
import dao.DaoLogin;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;


public class LoginUseCaseImpl implements LoginUseCase {


    private DaoLogin daoLogin;

    @Inject
    public LoginUseCaseImpl(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    private Either<ErrorApp, Object> validateUser(Usuario usuario) {
        if (usuario.nombre().equals("error")) {
            return Either.left(new Error3("error nombre"));
        }
        return Either.right(null);
    }


    private Either<ErrorApp, Object> validatePassword(Usuario usuario) {
        if (usuario.password().equals("error")) {
            return Either.left(new Error3("error password"));
        }
        return Either.right(null);
    }
    @Override
    public Either<ErrorApp, ResultMio<Boolean>> doLogin(Usuario usuario) {

        return validateUser(usuario)
                .flatMap(aBoolean -> validatePassword(usuario))
                .flatMap(aBoolean -> daoLogin.doLogin(usuario));
    }
}
