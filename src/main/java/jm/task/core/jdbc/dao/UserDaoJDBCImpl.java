package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Statement statement;
    PreparedStatement preparedStatement;
    Connection connection = null;
    private final String create = "CREATE TABLE IF NOT EXISTS `mydb`.`users1` (\n" +
            "  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `lastName` VARCHAR(45) NULL,\n" +
            "  `age` TINYINT(3) NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(create);
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users1");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void saveUser(String name1, String lastName1, byte age1) {
        String exeUp = "INSERT INTO users1 (name, lastName, age) VALUES (?,?,?)";
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(exeUp);
            preparedStatement.setString(1, name1);
            preparedStatement.setString(2, lastName1);
            preparedStatement.setInt(3, age1);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name1 + " добавлен в базу данных ");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Транзакция откатывается назад");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
            System.out.println("Ошибка добавления User");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeUserById(long id1) {
        String removeUser = "DELETE FROM users1 WHERE id = ?";
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(removeUser);
            preparedStatement.setLong(1, id1);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Транзакция откатывается назад");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            System.out.println("Ошибка удаления User по id!");
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех юзеров!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users1");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Транзакция откатывается назад");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
            System.out.println("ошибка очистки таблицы");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
