package by.tms.project.dao;

import by.tms.project.entity.user.Patient;
import by.tms.project.exception.DaoException;

import java.util.List;

public interface PatientDao {

    void add(Patient patient) throws DaoException;

    List<Patient> findAll();

}
