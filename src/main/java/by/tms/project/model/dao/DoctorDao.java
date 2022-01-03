package by.tms.project.model.dao;

import by.tms.project.exception.DaoException;
import by.tms.project.model.entity.Doctor;

import java.util.List;

public interface DoctorDao {

    List<Doctor> findAllDoctorsByExperience() throws DaoException;
    List<Doctor> findAllByCategory() throws DaoException;
    List<Doctor> findBySpeciality() throws DaoException;


}
