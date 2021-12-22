package by.tms.project.dao;

import by.tms.project.entity.Doctor;
import by.tms.project.exception.DoctorRepeatException;
import by.tms.project.exception.UserRepeatException;

import java.util.List;

public interface DoctorDao {

    void add(Doctor doctor) throws DoctorRepeatException;

    List<Doctor> get();

    void remove(Doctor doctor );

    void update( Doctor doctor);
}
