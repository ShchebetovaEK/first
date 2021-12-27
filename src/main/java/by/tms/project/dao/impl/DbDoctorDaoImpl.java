package by.tms.project.dao.impl;

import by.tms.project.connection.MySQLConnection;
import by.tms.project.dao.DoctorDao;
import by.tms.project.entity.user.Doctor;
import by.tms.project.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDoctorDaoImpl implements DoctorDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String sqlAdd = "INSERT INTO doctors (category, experience, users_id, specialities_id_speciality) VALUES (?,?,?,?)";
    private static final String sqlFindAll = "SELECT  (category, experience, users_id, specialities_id_speciality) FROM doctors ";

    @Override
    public void add(Doctor doctor) throws DaoException {
        try (Connection connection = MySQLConnection.getConnection()) {
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

    @Override
    public List<Doctor> findAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection()) {
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


}
