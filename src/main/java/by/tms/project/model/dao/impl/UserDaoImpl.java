package by.tms.project.model.dao.impl;


import by.tms.project.model.dao.AbstractDao;
import by.tms.project.model.dao.ColumnName;
import by.tms.project.model.dao.UserDao;
import by.tms.project.model.entity.Role;
import by.tms.project.model.entity.User;
import by.tms.project.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.tms.project.model.util.security.PasswordEncryptor;
import org.mindrot.jbcrypt.BCrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users""";
    private static final String SQL_SELECT_BY_ID = """ 
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.id =?""";
    private static final String SQL_SELECT_BY_LOGIN = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.login =?""";
    private static final String SQL_SELECT_BY_FIRST_NAME = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.first_name=?""";
    private static final String SQL_SELECT_BY_LAST_NAME = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.last_name =?""";
    private static final String SQL_SELECT_BY_EMAIL = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.email =?""";
    private static final String SQL_SELECT_BY_PHONE_NUMBER = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.phone_number=?""";
    private static final String SQL_SELECT_BY_FIRST_NAME_AND_ROLE = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.first_name =?  AND users.role=?""";
    private static final String SQL_SELECT_BY_LAST_NAME_AND_ROLE = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.last_name =? AND users.role=?""";
    private static final String SQL_SELECT_ALL_BY_ROLE = """
            SELECT users.id,users.role,users.login,users.password,users.first_name,users.last_name,
            users.data_birthday,users.address,users.phone_number,users.email FROM users WHERE users.role =?""";
    private static final String SQL_SET_LOGIN = """
            UPDATE users SET users.login=? WHERE users.id=?""";
    private static final String SQL_SET_PASSWORD = """
            UPDATE users SET users.password=? WHERE users.id=?""";
    private static final String SQL_CREATE_USER = """
            INSERT INTO users(id,role,login,password,first_name,last_name,data_birthday,address,phone_number,email) VALUES (?,?,?,?,?,?,?,?,?,?)""";
    private static final String SQL_UPDATE_USER = """
            UPDATE users SET users.role=?,users.login=?,users.password=?,users.first_name=?,
            users.last_name=?,users.data_birthday=?,users.address=?,users.phone_number=?,users.email=? WHERE users.id=?""";
    private static final String SQL_DELETE_USER_BY_ID = """
            DELETE FROM users WHERE users.id =?""";
    private static final String SQL_CHECK_OLD_PASSWORD = """
            SELECT users.password FROM users WHERE users.id=? AND users.login=?""";
    private static final String SQL_BY_CURRENT_LOGIN = """
            SELECT users.id,users.login FROM users WHERE users.login=?""";

    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
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
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = Optional.of(getUserInfo(resultSet));
                }
            } catch (SQLException e) {
                logger.error("ResultSet in method findById failed", e);
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");

        }
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setString(1, login);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = Optional.ofNullable(getUserInfo(resultSet));
                }
            } catch (SQLException e) {
                logger.error("ResultSet in method FindByLogin failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByLogin failed", e);
            throw new DaoException("findByLogin failed", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PHONE_NUMBER)) {
            preparedStatement.setString(1, phoneNumber);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = Optional.of(getUserInfo(resultSet));
                }
            } catch (SQLException e) {
                logger.error("ResultSet in method findByPhoneNumber failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByPhoneNumber failed", e);
            throw new DaoException("findByPhoneNumber failed", e);
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = Optional.of(getUserInfo(resultSet));
                }
            } catch (SQLException e) {
                logger.error("ResultSet in method findByEmail failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByEmail failed", e);
            throw new DaoException("findByEmail failed", e);
        }
        return user;
    }

    @Override
    public List<User> findByFirstName(String firstName) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FIRST_NAME)) {
            preparedStatement.setString(1, firstName);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = getUserInfo(resultSet);
                    userList.add(user);
                }
            } catch (SQLException e) {
                logger.error("ResultSet in method findByFirstName failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByFirstName failed", e);
            throw new DaoException("findByFirstName failed", e);
        }
        return userList;
    }

    @Override
    public List<User> findByLastName(String lastName) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = getUserInfo(resultSet);
                    userList.add(user);
                }
            } catch (SQLException e) {
                logger.error("Resultset in method findByLastName failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByLastName failed", e);
            throw new DaoException("findByLastName failed", e);
        }
        return userList;
    }

    @Override
    public List<User> findByFirstNameAndRole(String firstName, Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_FIRST_NAME_AND_ROLE)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, String.valueOf(role));
            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User user = getUserInfo(resultSet);
                    userList.add(user);
                }
            } catch (SQLException e) {
                logger.error("Resultset in method findByFirstNameAndRole failed", e);
            }
        } catch (SQLException e) {
            logger.error("findByFirstNameAndRole", e);
            throw new DaoException("findByFirstNameAndRole", e);
        }
        return userList;
    }


    @Override
    public List<User> findByLastNameAndRole(String lastName, Role role) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LAST_NAME_AND_ROLE)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, String.valueOf(role));
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = getUserInfo(resultSet);
                    userList.add(user);
                }
            } catch (SQLException e) {
                logger.error("Resultset in method findByLastNameAndRole", e);
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
            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                if (resultSet.getString(ColumnName.USERS_LOGIN).equals(login)) {
                    result = 1;
                }
            } catch (SQLException e) {
                logger.error("resultSet in method ifExistByLogin failed", e);
            }
        } catch (SQLException e) {
            logger.error("ifExistByLogin failed", e);
            throw new DaoException("ifExistByLogin failed", e);
        }
        return result > 0;
    }

    @Override
    public boolean ifExistByEmail(String email) throws DaoException {
        byte result = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                if (resultSet.getString(ColumnName.USERS_EMAIL).equals(email)) {
                    result = 1;
                }
            } catch (SQLException e) {
                logger.error("resultSet in method ifExistByEmail failed", e);
            }
        } catch (SQLException e) {
            logger.error("ifExistByEmail failed", e);
            throw new DaoException("ifExistByEmail failed", e);
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
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                if (BCrypt.checkpw(oldPassword, resultSet.getString(ColumnName.USERS_PASSWORD))) {
                    result = true;
                }
            } catch (SQLException e) {
                logger.error("resultset in method checkOldPassword failed", e);
            }
        } catch (SQLException e) {
            logger.error("checkOldPassword failed", e);
            throw new DaoException("checkOldPassword failed", e);
        }
        return result;
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

    @Override
    public boolean checkUserLogin(String login) throws DaoException {
        boolean exist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist = resultSet.next();
        } catch (SQLException e) {
            logger.error("", e);
            throw new DaoException("", e);
        }
        return exist;
    }

    public User getUserInfo(ResultSet resultSet) throws SQLException {
        return (new User.UserBuilder()
                .setId(resultSet.getLong(ColumnName.USERS_ID))
                .setRole(Role.valueOf(resultSet.getString(ColumnName.USERS_ROLE)))
                .setLogin(resultSet.getString(ColumnName.USERS_LOGIN))
                .setPassword(resultSet.getString(ColumnName.USERS_PASSWORD))
                .setFirstName(resultSet.getString(ColumnName.USERS_FIRST_NAME))
                .setLastName(resultSet.getString(ColumnName.USERS_LAST_NAME))
                .setDataBirthday(LocalDate.parse(resultSet.getString(ColumnName.USERS_DATA_BIRTHDAY)))
                .setAddress(resultSet.getString(ColumnName.USERS_ADDRESS))
                .setPhoneNumber(resultSet.getString(ColumnName.USERS_PHONE_NUMBER))
                .setEmail(resultSet.getString(ColumnName.USERS_EMAIL))
                .createUser());
    }
}
