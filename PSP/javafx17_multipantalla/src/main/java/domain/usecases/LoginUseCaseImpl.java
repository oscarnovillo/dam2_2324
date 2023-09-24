package domain.usecases;

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


    @Override
    public Either<ErrorApp, ResultMio<Boolean>> doLogin(Usuario usuario) {

        return daoLogin.doLogin(usuario);

    }
}
