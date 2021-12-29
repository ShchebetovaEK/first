package by.tms.project.model.dao;

import by.tms.project.model.entity.Role;
import by.tms.project.model.entity.User;
import by.tms.project.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface UserDao  {


    public abstract Optional<User> findByLogin(String login) throws DaoException;

    public abstract Optional<User> findByPhoneNumber(String phoneNumber) throws DaoException;

    public abstract Optional<User> findByEmail(String email) throws DaoException;

    public abstract List<User> findByFirstName(String firstName) throws DaoException;

    public abstract List<User> findByLastName(String lastName) throws DaoException;

    public abstract List<User> findByFirstNameAndRole(String firstName, Role role) throws DaoException;

    public abstract List<User> findByLastNameAndRole(String lastName, Role role) throws DaoException;

    public abstract boolean ifExistByLogin (String login) throws  DaoException;

    public abstract boolean ifExistByEmail (String email) throws DaoException;

    public abstract boolean setLogin (User user, String login) throws DaoException;

    public abstract boolean setPassword (User user, String password) throws DaoException;

    public abstract boolean checkOldPassword(User user, String oldPassword) throws DaoException;

    public abstract boolean checkUserLogin(String login) throws DaoException;
}
