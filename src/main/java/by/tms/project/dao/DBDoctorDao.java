package by.tms.project.dao;

import by.tms.project.connection.MySQLConnection;
import by.tms.project.entity.Doctor;
import by.tms.project.exception.DoctorRepeatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDoctorDao implements DoctorDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void add(Doctor doctor) throws DoctorRepeatException {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "INSERT INTO doctors (category, experience, users_id, specialities_id_speciality) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getCategory());
            statement.setString(2, doctor.getExperience());
            statement.setLong(3, doctor.getId());
            statement.setInt(4, doctor.getSpeciality());
        } catch (SQLException e) {
            logger.error("SQL exception add doctor");
        }
    }

    @Override
    public List<Doctor> get() {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors ");
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
    public void remove(Doctor doctor) {
        //todo
    }

    @Override
    public void update(Doctor doctor) {
        //todo
    }
}
