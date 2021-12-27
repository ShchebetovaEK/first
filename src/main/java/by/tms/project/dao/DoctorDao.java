package by.tms.project.dao;

import by.tms.project.entity.user.Doctor;
import by.tms.project.exception.DaoException;

import java.util.List;

public interface DoctorDao {

    void add(Doctor doctor) throws DaoException;

    List<Doctor> findAllDoctors();


}
