package ui.pantallas.login;

import common.Error2;
import common.Success;
import domain.modelo.Usuario;
import domain.usecases.LoginUseCase;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginViewModelTest {

    // a testear
    @InjectMocks
    private LoginViewModel viewModel;

    //dependencias
    @Mock
    private LoginUseCase loginUseCase;


    @BeforeEach
    void setUp() {

    }

    @Test
    void getState() {


    }

    @Test
    void doLogin() {

        //given
        Usuario user = new Usuario("admin", "");
        LoginState stateEsperado  = new LoginState(true,null);
        //when(loginUseCase.doLogin(argThat(usuario -> !usuario.getNombre().equals("admin")))).thenReturn(false);
        when(loginUseCase.doLogin(argThat(usuario -> usuario.nombre().equals("admin")))).thenReturn(Either.right(new Success<>(true)));

        //when
        viewModel.doLogin(user);

        //then
        LoginState state = viewModel.getState().getValue();
        assertThat(state).hasFieldOrPropertyWithValue("loginOK",stateEsperado.isLoginOK());




    }

    @Test
    void doLoginIncorrecto() {

        //given
        Usuario user = new Usuario("otro", "");
        LoginState stateEsperado  = new LoginState(false,"usuario "+user.nombre()+" no valido");
        when(loginUseCase.doLogin(argThat(usuario -> usuario.nombre().equals("otro")))).thenReturn(Either.left(new Error2("false")));

        //when
        viewModel.doLogin(user);

        //then
        LoginState state = viewModel.getState().getValue();

        assertAll(() -> assertThat(state).hasFieldOrPropertyWithValue("loginOK",stateEsperado.isLoginOK()),
                () -> assertThat(state).hasFieldOrPropertyWithValue("error",stateEsperado.getError()));
    }

}
