package org.example.springjavafx.seguridad;


//import jakarta.enterprise.inject.Produces;

import org.example.springjavafx.seguridad.impl.EncriptacionAES;

public class ProviderSeguridad {


    //@Produces
    public Encriptacion getEncriptacion()
    {
        return new EncriptacionAES();
    }

}
