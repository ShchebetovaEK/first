package by.tms.project.dao.impl;


import by.tms.project.dao.UserDao;
import by.tms.project.entity.user.Role;
import by.tms.project.entity.user.User;
import by.tms.project.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.tms.project.security.PasswordEncryptor;
import org.mindrot.jbcrypt.BCrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.tms.project.dao.impl.ColumnName.*;

public final class DbUserDaoImpl extends UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users";
    private static final String SQL_SELECT_BY_ID = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.id =?";
    private static final String SQL_SELECT_BY_LOGIN = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.login =?";
    private static final String SQL_SELECT_BY_FIRST_NAME = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.first_name=?";
    private static final String SQL_SELECT_BY_LAST_NAME = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.last_name =?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.email =?";
    private static final String SQL_SELECT_BY_PHONE_NUMBER = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.phone_number=?";
    private static final String SQL_SELECT_BY_FIRST_NAME_AND_ROLE = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.first_name =?  AND users.role=?";
    private static final String SQL_SELECT_BY_LAST_NAME_AND_ROLE = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.last_name =? AND users.role=?";
    private static final String SQL_SELECT_ALL_BY_ROLE = "SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.role =?";
    private static final String SQL_SET_LOGIN = "UPDATE users SET users.login=? WHERE users.id=?";
    private static final String SQL_SET_PASSWORD = "UPDATE users SET users.password=? WHERE users.id=?";
    private static final String SQL_CREATE_USER = "INSERT INTO users(id,role,login,password,first_name,last_name,data_birthday,address,phone_number,email) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET users.role=?,users.login=?,users.password=?,users.first_name=?,users.last_name=?,users.data_birthday=?,users.address=?,users.phone_number=?,users.email=? WHERE users.id=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE users.id =?";
    private static final String SQL_CHECK_OLD_PASSWORD = "SELECT users.password FROM users WHERE users.id=? AND users.login=?";
    private static final String SQL_BY_CURRENT_LOGIN = "SELECT users.id,users.login FROM users WHERE users.login=?";
//    private static final String SQL_ADD = "INSERT INTO users (role, login, password, first_name, last_name, data_birthday, address, phone_number, email) VALUES (?,?,?,?,?,?,?,?,?)";
//    private static final String SQL_FIND_ALL = "SELECT (role, login, password, first_name, last_name, data_birthday, address, phone_number, email) FROM users";


    private static DbUserDaoImpl instance;

    public DbUserDaoImpl() {

    }

    public static DbUserDaoImpl getInstance() {
        if (instance == null) {
            instance = new DbUserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.ofNullable(getUserInfo(resultSet));
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PHONE_NUMBER)) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getUserInfo(resultSet));
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getUserInfo(resultSet));
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FIRST_NAME)) {
            preparedStatement.setString(1, firstName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserInfo(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return userList;
    }

    @Override
    public List<User> findByLastName(String lastName) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserInfo(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return userList;
    }

    @Override
    public List<User> findByFirstNameAndRole(String firstName, Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FIRST_NAME_AND_ROLE)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, String.valueOf(role));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserInfo(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return userList;
    }


    @Override
    public List<User> findByLastNameAndRole(String lastName, Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LAST_NAME_AND_ROLE)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, String.valueOf(role));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserInfo(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("0", e);
            throw new DaoException("", e);
        }
        return userList;
    }

    @Override
    public boolean ifExistByLogin(String login) throws DaoException {
        byte result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_BY_CURRENT_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getString(USERS_LOGIN).equals(login)) {
                result = 1;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result > 0;
    }

    @Override
    public boolean ifExistByEmail(String email) throws DaoException {
        byte result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getString(USERS_EMAIL).equals(email)) {
                result = 1;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result > 0;
    }

    @Override
    public boolean setLogin(User entity, String login) throws DaoException {
        int result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setLong(2, entity.getId());
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result > 0;

    }

    @Override
    public boolean setPassword(User entity, String password) throws DaoException {
        int result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_PASSWORD)) {
            String resultPassword = PasswordEncryptor.encrypt(password);
            preparedStatement.setString(1, resultPassword);
            preparedStatement.setString(2, entity.getLogin());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result > 0;

    }

    @Override
    public boolean checkOldPassword(User entity, String oldPassword) throws DaoException {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_OLD_PASSWORD)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (BCrypt.checkpw(oldPassword, resultSet.getString(USERS_PASSWORD))) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserInfo(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error(" query has failed", e);
            throw new DaoException("query has failed", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(getUserInfo(resultSet));
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");

        }
        return user;
    }

    @Override
    public boolean create(User entity) throws DaoException {
        int result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(1, String.valueOf(entity.getRole()));
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setDate(6, Date.valueOf(entity.getDataBirthday()));
            preparedStatement.setString(7, entity.getAddress());
            preparedStatement.setString(8, entity.getPhoneNumber());
            preparedStatement.setString(9, entity.getEmail());
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");

        }
        return result > 0;

    }

    @Override
    public boolean update(User entity) throws DaoException {
        int result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, String.valueOf(entity.getRole()));
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setDate(6, Date.valueOf(entity.getDataBirthday()));
            preparedStatement.setString(7, entity.getAddress());
            preparedStatement.setString(8, entity.getPhoneNumber());
            preparedStatement.setString(9, entity.getEmail());
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");

        }
        return result > 0;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        int result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");

        }
        return result > 0;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        int result;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, entity.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return result > 0;
    }

    public User getUserInfo(ResultSet resultSet) throws SQLException {
        return (new User.UserBuilder()
                .setId(resultSet.getLong(USERS_ID))
                .setRole(Role.valueOf(resultSet.getString(USERS_ROLE)))
                .setLogin(resultSet.getString(USERS_LOGIN))
                .setPassword(resultSet.getString(USERS_PASSWORD))
                .setFirstName(resultSet.getString(USERS_FIRST_NAME))
                .setLastName(resultSet.getString(USERS_LAST_NAME))
                .setDataBirthday(LocalDate.parse(resultSet.getString(USERS_DATA_BIRTHDAY)))
                .setAddress(resultSet.getString(USERS_ADDRESS))
                .setPhoneNumber(resultSet.getString(USERS_PHONE_NUMBER))
                .setEmail(resultSet.getString(USERS_EMAIL))
                .createUser());
    }


}

//    public void addUser(User user) throws DaoException {
//        try (Connection connection = MySQLConnection.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(SQL_ADD);
//            statement.setString(1, String.valueOf(user.getRole()));
//            statement.setString(2, user.getLogin());
//            statement.setString(3, user.getPassword());
//            statement.setString(4, user.getFirstName());
//            statement.setString(5, user.getLastName());
//            statement.setDate(6, Date.valueOf(user.getDataBirthday()));
//            statement.setString(7, user.getAddress());
//            statement.setString(8, user.getPhoneNumber());
//            statement.setString(9, user.getEmail());
//
//        } catch (SQLException e) {
//            logger.log(Level.INFO, "Can't  {}", e.getMessage());
//            throw new DaoException();
//        }
//    }
//
//
//    public List<User> findAllUsers() throws DaoException {
//        List<User> users = new ArrayList<>();
//        try {
//            Connection connection = MySQLConnection.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong(USERS_ID));
//                user.setRole(Role.valueOf(resultSet.getString(USERS_ROLE)));
//                user.setLogin(resultSet.getString(USERS_LOGIN));
//                user.setPassword(resultSet.getString(USERS_PASSWORD));
//                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
//                user.setLastName(resultSet.getString(USERS_LAST_NAME));
//                user.setDataBirthday(LocalDate.parse(resultSet.getString(USERS_DATA_BIRTHDAY)));
//                user.setAddress(resultSet.getString(USERS_ADDRESS));
//                user.setPhoneNumber(resultSet.getString(USERS_PHONE_NUMBER));
//                user.setEmail(resultSet.getString(USERS_EMAIL));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            logger.info("list user {}", e.getMessage());
//            throw new DaoException(e);
//        }
//        return users;
//    }

