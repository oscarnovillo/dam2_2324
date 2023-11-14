package dao;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author oscar
 */
@Singleton
public class DBConnectionPool {

    private DataSource hirakiDatasource = null;

    private Configuration config;

    @Inject
    public DBConnectionPool(Configuration config) {

        this.config = config;

    }

    public void cargarPool()
    {
        hirakiDatasource = getDataSourceHikari();
    }

    public Connection getConnection() throws Exception {

        Connection connection;

        connection = hirakiDatasource.getConnection();

        return connection;
    }


    private DataSource getDataSourceHikari() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(this.config.getRuta());
        config.setUsername(this.config.getUser());
        config.setPassword(this.config.getPassword());
        config.setDriverClassName(this.config.getDriver());
        config.setMaximumPoolSize(1);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource datasource = new HikariDataSource(config);

        return datasource;
    }

    public DataSource getDataSource() {
        // Creates a new instance of DriverManagerDataSource and sets
        // the required parameters such as the Jdbc Driver class,
//        MysqlDataSource mysql = new MysqlConnectionPoolDataSource();
//        mysql.setUrl(Configuration.getInstance().getUrlDB());
//        mysql.setUser(Configuration.getInstance().getUserDB());
//        mysql.setPassword(Configuration.getInstance().getPassDB());

        // Jdbc URL, database user name and password.
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(Configuration.getInstance().getDriverDB());
//        dataSource.setUrl(Configuration.getInstance().getUrlDB());
//        dataSource.setUsername(Configuration.getInstance().getUserDB());
//        dataSource.setPassword(Configuration.getInstance().getPassDB());

        //return mysql;
        return hirakiDatasource;
    }

    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @PreDestroy
    public void closePool() {
       ((HikariDataSource) hirakiDatasource).close();
    }

}

