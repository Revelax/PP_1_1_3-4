package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private final String URL = "jdbc:mysql://localhost:3306/mydb";
    private final String USER = "root";
    private final String PASSWORD = "251191";

    Connection connection;

    public  Util() {
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }

        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с БД!");
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }

}

