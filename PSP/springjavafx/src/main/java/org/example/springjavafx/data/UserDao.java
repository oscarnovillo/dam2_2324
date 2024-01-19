package org.example.springjavafx.data;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {


    private final JdbcClient jdbcClient;

    public UserDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void insertUser(String name, String password) {
        jdbcClient.sql("insert into users (name, password) values (?, ?)")
                .param(1,name)
                .param(2, password)
                .update();
    }

}
