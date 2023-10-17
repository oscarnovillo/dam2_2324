package org.example.jakarta.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletMVC", value = "/ServletMVC")
public class ServletMVC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var numero = Integer.parseInt(request.getParameter("numero"));

        var lista = new ArrayList<Integer>();
        for (int i = 1; i <= numero; i++) {
            lista.add(i);
        }
        request.setAttribute("list", lista);
        request.getRequestDispatcher("primer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
