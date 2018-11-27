package com.kieran.api.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DaoConnection{
    // JDBC driver name and database URL
    static final String url = "jdbc:postgresql://206.189.30.130:5432/website-api";
    private final String user = "[username]";
    private final String password = "[password]";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return conn;
    }
}
