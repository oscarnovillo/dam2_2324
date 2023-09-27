package ui.pantallas.login;

import common.*;
import domain.modelo.Usuario;
import domain.usecases.LoginUseCase;
import jakarta.inject.Inject;
import javafx.beans.property.*;

public class LoginViewModel {


    private final LoginUseCase loginUseCase;

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        state = new SimpleObjectProperty<>(new LoginState(false,null));
    }

    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }


    public void doLogin(Usuario usuario) {

        loginUseCase.doLogin(usuario)
                .peek(booleanResultMio -> {
                    switch (booleanResultMio){
                        case Loading<Boolean> l -> { }
                        case Success<Boolean> s -> state.setValue(new LoginState(s.getData(), null));
                        case ErrorTest<Boolean> errorTest -> {
                        }
                    }

                })
                .peekLeft(errorApp -> {
                    switch(errorApp){

                        case Error3 error3 -> { var i = 2;
                        }
                        case Error2 error2 -> {
                        }
                    }

                    state.setValue(new LoginState(false, errorApp.getMensaje()));
                } );



    }
}
