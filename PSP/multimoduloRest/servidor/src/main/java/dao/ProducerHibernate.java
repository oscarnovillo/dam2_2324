package dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProducerHibernate {
    private SessionFactory ourSessionFactory;

    @Produces
    @Singleton
    public SessionFactory getHibernateSessionFactory( config.Configuration configurationApp) {
        try {
            Configuration configuration = new Configuration();

            configuration.configure()
                    .setProperty("hibernate.connection.url", configurationApp.getRuta())
                    .setProperty("hibernate.connection.username", configurationApp.getUser())
                    .setProperty("hibernate.connection.password", configurationApp.getPassword())
                    .setProperty("hibernate.connection.driver_class", configurationApp.getDriver())
            ;

            //configuration.setProperty()
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }



    @Produces
    @RequestScoped
    public Session getSession(SessionFactory ourSessionFactory) throws HibernateException {
        return ourSessionFactory.openSession();
    }
}
