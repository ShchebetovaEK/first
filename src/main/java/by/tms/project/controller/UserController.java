package by.tms.project.controller;

import by.tms.project.dao.DBUserDao;
import by.tms.project.dao.UserDao;
import by.tms.project.entity.User;
import by.tms.project.exception.UserRepeatException;

import java.util.List;

public class UserController {
    private UserDao userDao = new DBUserDao();

    public void add(User user) throws UserRepeatException {
        userDao.add(user);
    }

    public List<User> get() {
        return userDao.get();
    }

    public void remove(long id) {
        //TODO
    }

    public void update(User user) {
        userDao.update(user);
    }
}
