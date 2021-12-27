package by.tms.project.controller;

import by.tms.project.dao.impl.DbPatientDaoImpl;
import by.tms.project.dao.PatientDao;
import by.tms.project.entity.user.Patient;
import by.tms.project.exception.DaoException;

import java.util.List;

public class PatientController {
    private PatientDao patientDao = new DbPatientDaoImpl();


    public void add(Patient patient) throws DaoException {
        patientDao.add(patient);
    }

    public List<Patient> findAll() {
        return patientDao.findAll();
    }

}


