package com.mogreene.spring_board;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {

    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://localhost:3306/spring_db";
    private static final String username = "root";
    private static final String password = "1234";

    @Test
    public void connectionTest() throws Exception {
        Class.forName(driver);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
