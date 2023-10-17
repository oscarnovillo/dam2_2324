package org.example.jakarta.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Visitas", value = "/Visitas")
public class Visitas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().setAttribute("visitas", (int) getServletContext().getAttribute("visitas") + 1);
        //contar visitas con session
        var session = req.getSession();
        var contador = (Integer) session.getAttribute("contador");
        if (contador== null) {

            //primera vez
            contador = 0;
        }


        contador++;
        session.setAttribute("contador", contador);
        resp.getWriter().println("Visitas DEL USUARIO: " + contador);
        resp.getWriter().println("Visitas TOTALES: " +  getServletContext().getAttribute("visitas"));

    }
}
