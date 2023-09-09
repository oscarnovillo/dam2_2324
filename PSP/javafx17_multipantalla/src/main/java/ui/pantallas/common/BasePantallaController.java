package ui.pantallas.common;

import ui.pantallas.principal.PrincipalController;


public class BasePantallaController {

    private PrincipalController principalController;

    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado()
    {

    }
}
