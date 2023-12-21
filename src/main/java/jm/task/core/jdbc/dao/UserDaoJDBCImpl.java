package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util util = new Util();
    Statement statement;
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

    public void createUsersTable() {
        try {
            //statement = util.getConnection().createStatement();
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

    public void dropUsersTable() {
        try {
            //statement = util.getConnection().createStatement();
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

    public void saveUser(String name, String lastName, byte age) {
        String exeUp = String.format("INSERT INTO users1 (name, lastName, age) VALUES ('%s','%s',%s)", name, lastName, age);
        try {
            //statement = util.getConnection().createStatement();
            statement.executeUpdate(exeUp);
            System.out.println("User с именем - " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка добавления User");
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeUserById(long id) {
        String removeUser = String.format("DELETE FROM users1 WHERE id = %s",id);
        try {
            //statement = util.getConnection().createStatement();
            statement.executeUpdate(removeUser);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления User по id!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }

    }

    public List<User> getAllUsers() {
        List <User> allUsers = new ArrayList<>();
        try {
            //statement = util.getConnection().createStatement();
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

    public void cleanUsersTable() {
        try {
            //statement = util.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM users1");
        } catch (SQLException e) {
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
