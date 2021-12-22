package by.tms.project.dao;

import by.tms.project.entity.User;
import by.tms.project.exception.UserRepeatException;

import java.util.List;


public interface UserDao {

    void add(User user) throws UserRepeatException;
    List<User> get();
    void remove(User user);
    void update(User user);
}
