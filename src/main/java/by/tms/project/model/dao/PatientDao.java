package by.tms.project.model.dao;


import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.Patient;

import java.util.List;

public interface PatientDao {

    List<Patient> findPatientByInsurance() throws DaoException;
    List<Patient> findPatientByAccountMin() throws DaoException;

}
