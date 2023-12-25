package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    //private UserDao userDaoJDBC = new UserDaoJDBCImpl();
    private UserDao userDaoHib = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        //userDaoJDBC.createUsersTable();
        userDaoHib.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        //userDaoJDBC.dropUsersTable();
        userDaoHib.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        //userDaoJDBC.saveUser(name, lastName, age);
        userDaoHib.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        //userDaoJDBC.removeUserById(id);
        userDaoHib.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        //return userDaoJDBC.getAllUsers();
        return userDaoHib.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        //userDaoJDBC.cleanUsersTable();
        userDaoHib.cleanUsersTable();
    }
}
