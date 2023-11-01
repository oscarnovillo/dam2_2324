package servlets;

import com.google.gson.Gson;
import domain.Equipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "Servlet", value = "/juan")
public class MIPrimerServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Equipo p = new Equipo( "1", "Madrid", "Madrid", "Santiago Bernabeu");
        Gson g = new Gson();
        g.toJson(p, resp.getWriter());

    }
}
