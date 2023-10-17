package org.example.jakarta.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class VisitasListener implements ServletContextListener {




    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("visitas", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
