package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    //UserDao userDaoJDBC = new UserDaoJDBCImpl();
    UserDao userDaoHib = new UserDaoHibernateImpl();
    public void createUsersTable() {
        //userDaoJDBC.createUsersTable();
        userDaoHib.createUsersTable();
    }

    public void dropUsersTable() {
        //userDaoJDBC.dropUsersTable();
        userDaoHib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHib.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDaoHib.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDaoHib.getAllUsers();
    }

    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDaoHib.cleanUsersTable();
    }
}
