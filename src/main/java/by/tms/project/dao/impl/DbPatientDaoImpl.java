package by.tms.project.dao.impl;

import by.tms.project.connection.MySQLConnection;
import by.tms.project.dao.PatientDao;
import by.tms.project.entity.user.Patient;
import by.tms.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbPatientDaoImpl implements PatientDao {
    private  static final Logger logger = LogManager.getLogger();
    private static final String sqlAdd = "INSERT INTO patients (insurance, account, users_id) VALUES (?,?,?)";
    private static final String sqlFindAll = "SELECT (insurance, account, users_id) FROM patients";

    @Override
    public void add(Patient patient) throws DaoException {
        try (Connection connection = MySQLConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlAdd);
            statement.setBoolean(1,patient.isInsurance());
            statement.setLong(2,patient.getAccount());
            statement.setLong(3,patient.getId());

        }catch (SQLException e){
            logger.log(Level.INFO,"SQLex {}",e.getMessage());
            throw new DaoException();
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        try{ Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);
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


}
