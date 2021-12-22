package by.tms.project.controller;

import by.tms.project.dao.DBDoctorDao;
import by.tms.project.dao.DoctorDao;
import by.tms.project.entity.Doctor;
import by.tms.project.exception.DoctorRepeatException;

import java.util.List;

public class DoctorController {
    private DoctorDao doctorDao = new DBDoctorDao();


    public void add(Doctor doctor) throws DoctorRepeatException {
        doctorDao.add(doctor);
    }

    public List<Doctor> get() {
        return doctorDao.get();
    }

    public void remove(long id) {
        //TODO
    }

    public void update(Doctor doctor) {
        doctorDao.update(doctor);
    }
}
