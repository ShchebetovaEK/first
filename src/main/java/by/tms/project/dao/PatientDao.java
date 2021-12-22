package by.tms.project.dao;

import by.tms.project.entity.Patient;
import by.tms.project.exception.PatientRepeatException;

import java.util.List;

public interface PatientDao {

    void add(Patient patient) throws PatientRepeatException;

    List<Patient> get();

    void remove(Patient patient);

    void update(Patient patient);
}
