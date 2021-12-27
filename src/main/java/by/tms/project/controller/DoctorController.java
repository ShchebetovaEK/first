package by.tms.project.controller;

import by.tms.project.dao.impl.DbDoctorDaoImpl;
import by.tms.project.dao.DoctorDao;
import by.tms.project.entity.user.Doctor;
import by.tms.project.exception.DaoException;

import java.util.List;

public class DoctorController {
    private DoctorDao doctorDao = new DbDoctorDaoImpl();


    public void add(Doctor doctor) throws DaoException {
        doctorDao.add(doctor);
    }

    public List<Doctor> findAll() {
        return doctorDao.findAllDoctors();
    }

}
