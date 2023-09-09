package ui.pantallas.listado;

import domain.modelo.Cromo;
import lombok.Data;

import java.util.List;

@Data
public class ListadoState {

    private final List<Cromo> cromos;
    private final String error;
    private final boolean cargando;
}
