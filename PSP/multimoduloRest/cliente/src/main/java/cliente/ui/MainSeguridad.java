package cliente.ui;

import cliente.data.DaoEstupido;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.example.seguridad.Encriptacion;


public class MainSeguridad {

    private static String sSecretKey = "boooooooooom!!!!";

    public static void main(String[] args) {

        String originalString = "howtodoinjava.com";

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        Encriptacion encriptacion = container.select(Encriptacion.class).get();

        String encryptedString = encriptacion.encriptar(originalString, sSecretKey);
        String decryptedString = encriptacion.desencriptar(encryptedString, sSecretKey);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }

}

