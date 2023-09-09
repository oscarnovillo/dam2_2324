package ui.pantallas.common;

public enum Pantallas {

    PANTALLA1 ("/fxml/pantalla1.fxml"),
    LOGIN ("/fxml/login.fxml"),
    LISTADO ("/fxml/listado.fxml"),
    DETALLE ("/fxml/detalle.fxml"),
    PANTALLANUEVA (ConstantesPantallas.FXML_PANTALLA_NUEVA_FXML);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
