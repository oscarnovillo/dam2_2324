import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miprimerRest.jakarta.common.Utils;


import java.io.IOException;


@WebServlet(name = "ServletMail",urlPatterns = {"/mail"})
public class ServletMail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MandarMail mail = new MandarMail();

        try {
            mail.generateAndSendEmail("oscar.novillo@gmail.com", "<html>generado <a href=\"http://localhost:8080/jax/Activacion?codigo="+ Utils.randomBytes()+"\" >marca</a> " + Utils.randomBytes() + "</html>"
                    , "mail de prueba");
            response.getWriter().println("correo enviado");
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());

        }


    }
}
