package miprimerRest.jakarta.servlet;

import domain.servicios.MandarMail;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletLogout", value = "/ServletLogout")

public class ServletLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MandarMail mandarMail = new MandarMail();
        try {
            mandarMail.generateAndSendEmail("oscar.novillo@gmail.com", "<html><a href=\"www.marca.com\">Hola</a></html>", "Hola");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        //request.logout();
        response.getWriter().println("Logout");
        //request.getSession().removeAttribute("USERLOGIN");
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
