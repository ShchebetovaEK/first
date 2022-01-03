package by.tms.project.model.dao.impl;

import by.tms.project.model.connection.CreateConnection;
import by.tms.project.model.dao.AbstractDao;
import by.tms.project.model.dao.PatientDao;
import by.tms.project.model.entity.Patient;
import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl  extends AbstractDao<Long,User> implements PatientDao {
    private  static final Logger logger = LogManager.getLogger();
    private static final String sqlAdd = "INSERT INTO patients (insurance, account, users_id) VALUES (?,?,?)";
    private static final String sqlFindAll = "SELECT (insurance, account, users_id) FROM patients";

    public void add(Patient patient) throws DaoException {
        try (Connection connection = CreateConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlAdd);
            statement.setBoolean(1,patient.isInsurance());
            statement.setLong(2,patient.getAccount());
            statement.setLong(3,patient.getId());

        }catch (SQLException e){
            logger.log(Level.INFO,"SQLex {}",e.getMessage());
            throw new DaoException();
        }
    }

    public List<Patient> findAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try{ Connection connection = CreateConnection.getConnection();
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
        return false;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        return false;
    }


    @Override
    public List<Patient> findPatientByInsurance() throws DaoException {
        return null;
    }

    @Override
    public List<Patient> findPatientByAccountMin() throws DaoException {
        return null;
    }
}
