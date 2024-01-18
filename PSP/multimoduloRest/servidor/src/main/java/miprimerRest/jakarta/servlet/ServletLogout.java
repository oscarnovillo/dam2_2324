package miprimerRest.jakarta.servlet;

import domain.servicios.MandarMail;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import miprimerRest.jakarta.common.Utils;

import java.io.IOException;

@WebServlet(name = "ServletLogout", value = "/ServletLogout")
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE)
)
public class ServletLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MandarMail mandarMail = new MandarMail();
        try {
            String codigo = Utils.randomBytes();
            mandarMail.generateAndSendEmail("oscar.novillo@gmail.com", "<html><body><a href=\"http://localhost:8080/miprimerREST-1.0-SNAPSHOT/activacion?act="+codigo+"\">Hola</a></body></html>", "Hola");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        request.logout();
        //request.getSession().removeAttribute("USERLOGIN");
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
