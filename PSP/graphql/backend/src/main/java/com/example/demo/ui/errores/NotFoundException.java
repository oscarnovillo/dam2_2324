package com.example.demo.ui.errores;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
       super("no se encontro el recurso ");
    }
}
