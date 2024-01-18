package org.example.springjavafx.seguridad;

public interface Encriptacion {

    String encriptar(String texto,String secret);

    String desencriptar(String texto,String secret);

}
