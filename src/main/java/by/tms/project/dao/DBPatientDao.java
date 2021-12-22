package by.tms.project.dao;

import by.tms.project.connection.MySQLConnection;
import by.tms.project.entity.Patient;
import by.tms.project.exception.PatientRepeatException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBPatientDao implements PatientDao{
    private  static final Logger logger = LogManager.getLogger();

    @Override
    public void add(Patient patient) throws PatientRepeatException {
        try (Connection connection = MySQLConnection.getConnection()){
            String sql = "INSERT INTO patients (insurance, account, users_id) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1,patient.isInsurance());
            statement.setLong(2,patient.getAccount());
            statement.setLong(3,patient.getId());

        }catch (SQLException e){
            logger.log(Level.INFO,"SQLex {}",e.getMessage());
        }
    }

    @Override
    public List<Patient> get() {
        List<Patient> patients = new ArrayList<>();
        try{ Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM patients");
            while (resultSet.next()){
                Patient patient = new Patient();
                patient.setInsurance(resultSet.getBoolean("insurance"));
                patient.setAccount(resultSet.getLong("account"));
                patient.setId(resultSet.getLong("id"));
                patients.add(patient);
            }
        }catch (SQLException e){
            logger.info("list user {}",e.getMessage());
        }
        return patients;
    }

    @Override
    public void remove(Patient patient) {
        //todo
    }

    @Override
    public void update(Patient patient) {
        //todo
    }
}
