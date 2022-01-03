package by.tms.project.model.dao;

import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.Role;
import by.tms.project.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserDao {


    Optional<User> findByLogin(String login) throws DaoException;

    public Optional<User> findByPhoneNumber(String phoneNumber) throws DaoException;

    public Optional<User> findByEmail(String email) throws DaoException;

    public List<User> findByFirstName(String firstName) throws DaoException;

    public List<User> findByLastName(String lastName) throws DaoException;

    public List<User> findByFirstNameAndRole(String firstName, Role role) throws DaoException;

    public List<User> findByLastNameAndRole(String lastName, Role role) throws DaoException;

    public List<User> findAllByRole(Role role) throws DaoException;

    public boolean ifExistByLogin(String login) throws DaoException;

    public boolean ifExistByEmail(String email) throws DaoException;

    public boolean setLogin(User user, String login) throws DaoException;

    public boolean setPassword(User user, String password) throws DaoException;

    public boolean checkOldPassword(User user, String oldPassword) throws DaoException;

    public boolean checkUserLogin(String login) throws DaoException;
}
