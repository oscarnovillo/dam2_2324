module domain {


    requires lombok;

    exports domain.modelo;
    exports domain.errores;

    opens domain.modelo;
}
