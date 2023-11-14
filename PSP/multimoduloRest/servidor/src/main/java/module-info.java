module miprimerREST {

    requires jakarta.jakartaee.web.api;
    requires mysql.connector.java;
    requires jakarta.mail;
    requires io.vavr;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.hikaricp;
    requires com.zaxxer.hikari;
    requires lombok;
    requires org.yaml.snakeyaml;
    requires java.sql;

    requires domain;
    requires seguridad;

    requires modelmapper;
    requires java.net.http;
    requires jjwt.api;


    //requires domain.errores;


}
