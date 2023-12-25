package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "251191";
    private static Connection connection;

    private Util() {

    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            if (!connection.isClosed()) {
//                System.out.println("Соединение с БД установлено!");
//            }
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с БД!");
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

