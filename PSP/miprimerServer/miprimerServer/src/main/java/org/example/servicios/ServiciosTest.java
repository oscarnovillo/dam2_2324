package org.example.servicios;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


 public class ServiciosTest {

    public String getMensaje() {
        return "Hola injectado tomcat mundo";
    }
}
