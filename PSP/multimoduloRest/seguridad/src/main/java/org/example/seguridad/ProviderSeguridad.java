package org.example.seguridad;

import org.example.seguridad.impl.EncriptacionAES;
//import jakarta.enterprise.inject.Produces;

public class ProviderSeguridad {


    //@Produces
    public Encriptacion getEncriptacion()
    {
        return new EncriptacionAES();
    }

}
