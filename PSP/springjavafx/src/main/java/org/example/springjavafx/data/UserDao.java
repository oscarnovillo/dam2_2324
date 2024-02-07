package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {


    private final JdbcClient jdbcClient;

    public UserDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public int insertUser(String name, String password) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into users (name, password) values (?, ?)")
                .param(1,name)
                .param(2, password)
                .update(keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteUser(String name) {
        jdbcClient.sql("delete from users where name = ?")
                .param(1,name)
                .update();
    }

    public void updateUser(String name, String password) {
        jdbcClient.sql("update users set password = ? where name = ?")
                .param(1,password)
                .param(2,name)
                .update();
    }

    public User selectUser(String name) {
        return jdbcClient.sql("select * from users where name = ?")
                .param(1,name)
                .query(User.class).single()
                ;
    }

}
