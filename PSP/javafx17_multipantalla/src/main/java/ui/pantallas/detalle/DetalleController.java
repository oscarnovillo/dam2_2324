package ui.pantallas.detalle;

import domain.modelo.Cromo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.pantallas.common.BasePantallaController;

public class DetalleController extends BasePantallaController {

    @FXML
    private TextField txtColeccion;
    @FXML
    private TextField txtNumero;

    @Override
    public void principalCargado() {
        Cromo m = getPrincipalController().getActualCromo();
        txtColeccion.setText(m.getCollecion());
        txtNumero.setText(""+m.getNumero());

    }
}
