/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;


/**
 * @author dam2
 */
@Getter
@Log4j2
@Singleton
public class Configuration {

    private String ruta;
    private String user;
    private String password;
    private String driver;

    void cargar(InputStream file) {

        try {
            Yaml yaml = new Yaml();

            Iterable<Object> it = null;

            it = yaml
                    .loadAll(file);

            Map<String, String> m = (Map) it.iterator().next();
            this.ruta = m.get("ruta");
            this.password = m.get("password");
            this.user = m.get("user");
            this.driver = m.get("driver");


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}


