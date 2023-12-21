package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        Util util = new Util();
//        Util.getSessionFactory();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Artem","Artemov", (byte) 30);
        userService.saveUser("Roman","Romanov", (byte) 23);
        userService.saveUser("Ivan","Ivanov", (byte) 45);
        userService.saveUser("Aleksandr","Aleksandrov", (byte) 29);

        userService.getAllUsers().stream().forEach(System.out::println);
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

