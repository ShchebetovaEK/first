package by.tms.project.model.dao.impl;

import by.tms.project.model.connection.CreateConnection;

import by.tms.project.model.dao.AbstractDao;
import by.tms.project.model.dao.DoctorDao;
import by.tms.project.model.entity.Doctor;
import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl extends AbstractDao<Long, User> implements DoctorDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String sqlAdd = "INSERT INTO doctors (category, experience, users_id, specialities_id_speciality) VALUES (?,?,?,?)";
    private static final String sqlFindAll = "SELECT  (category, experience, users_id, specialities_id_speciality) FROM doctors ";


    public void add(Doctor doctor) throws DaoException {
        try (Connection connection = CreateConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlAdd);
            statement.setString(1, doctor.getCategory());
            statement.setString(2, doctor.getExperience());
            statement.setLong(3, doctor.getId());
            statement.setInt(4, doctor.getSpeciality());
        } catch (SQLException e) {
            logger.error("SQL exception add doctor");
            throw new DaoException();
        }
    }


    public List<Doctor> findAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = CreateConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setCategory(resultSet.getString("category"));
                doctor.setExperience(resultSet.getString("experience"));
                doctor.setId(resultSet.getLong("user_id"));
                doctor.setSpeciality(resultSet.getInt("specialities_id_speciality"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            logger.error("SQl List doctors exception");
        }
        return doctors;
    }


    @Override
    public List<User> findAll() throws DaoException {
        //todo
        return null;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(User entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(User entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long entity) throws DaoException {
       //todo
        return false;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
      //todo
        return false;
    }

    @Override
    public List<Doctor> findAllDoctorsByExperience() throws DaoException {
        //todo
        return null;
    }

    @Override
    public List<Doctor> findAllByCategory() throws DaoException {
        //todo
        return null;
    }

    @Override
    public List<Doctor> findBySpeciality() throws DaoException {
        /// TODO: 31.12.2021  
        return null;
    }
}
