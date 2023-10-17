package org.example.jakarta.servlets;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.jakarta.domain.Equipo;
import org.example.servicios.ServiciosTest;

import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {

    private  ServiciosTest serviciosTest;


    @Inject
    public Servlet(ServiciosTest serviciosTest) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Equipo p = new Equipo( "1", "Madrid", "Madrid", "Santiago Bernabeu");
        Gson g = new Gson();
        g.toJson(p, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
