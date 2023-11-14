package miprimerRest.jakarta.servlet;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.seguridad.Encriptacion;

import java.io.IOException;

@WebServlet(name = "ServletSeguridad", value = "/ServletSeguridad")
public class ServletSeguridad extends HttpServlet {

    private Pbkdf2PasswordHash passwordHash;

    private final Encriptacion encriptacion;


    @Inject
    public ServletSeguridad(Pbkdf2PasswordHash passwordHash, Encriptacion encriptacion) {
        this.passwordHash = passwordHash;
        this.encriptacion = encriptacion;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String sSecretKey = "boooooooooom!!!!";
        String originalString = "howtodoinjava.com";


        String encryptedString = encriptacion.encriptar(originalString, sSecretKey);
        String decryptedString = encriptacion.desencriptar(encryptedString, sSecretKey);

        response.getWriter().println(originalString);
        response.getWriter().println(encryptedString);
        response.getWriter().println(decryptedString);


    }

}
