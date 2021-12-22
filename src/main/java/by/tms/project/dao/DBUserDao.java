package by.tms.project.dao;

import by.tms.project.connection.MySQLConnection;
import by.tms.project.entity.Role;
import by.tms.project.entity.User;
import by.tms.project.exception.UserRepeatException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUserDao implements UserDao{
   private  static final Logger logger = LogManager.getLogger();
    @Override
    public void add(User user) throws UserRepeatException {
      try (Connection connection = MySQLConnection.getConnection()){
          String sql = "INSERT INTO users (role, login, password, first_name, last_name, data_birthday, address, phone_number, email) VALUES (?,?,?,?,?,?,?,?,?)";
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, String.valueOf(user.getRole()));
          statement.setString(2,user.getLogin());
          statement.setString(3,user.getPassword());
          statement.setString(4, user.getFirstName());
          statement.setString(5,user.getLastName());
          statement.setDate(6, Date.valueOf(user.getDataBirthday()));
          statement.setString(7,user.getAddress());
          statement.setString(8,user.getPhoneNumber());
          statement.setString(9,user.getEmail());

    }catch (SQLException e){
          logger.log(Level.INFO,"SQLex {}",e.getMessage());
      }
    }

    @Override
    public List<User> get() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setDataBirthday(LocalDate.parse(resultSet.getString("data_birthday")));
                user.setAddress(resultSet.getString("address"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        }catch (SQLException e){
            logger.info("list user {}",e.getMessage());
        }
        return users;
    }

    @Override
    public void remove(User user) {
        //todo
    }

    @Override
    public void update(User user) {
        //todo
    }
}
