package by.tms.project.controller;

import by.tms.project.dao.DBPatientDao;
import by.tms.project.dao.PatientDao;
import by.tms.project.entity.Patient;
import by.tms.project.exception.PatientRepeatException;

import java.util.List;

public class PatientController {
    private PatientDao patientDao = new DBPatientDao();


    public void add(Patient patient) throws PatientRepeatException {
        patientDao.add(patient);
    }

    public List<Patient> get() {
        return patientDao.get();
    }

    public void remove(long id) {
        //TODO
    }

    public void update(Patient patient) {
        patientDao.update(patient);
    }
}


