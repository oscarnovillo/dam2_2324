package domain.usecases;

import domain.modelo.Usuario;

public interface LoginUseCase {
    boolean doLogin(Usuario usuario);
}
