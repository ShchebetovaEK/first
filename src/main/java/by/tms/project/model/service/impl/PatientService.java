package by.tms.project.model.service.impl;

import by.tms.project.exception.ServiceException;
import jakarta.servlet.http.HttpSession;

public interface PatientService {

    boolean toUpBalance(String login, long refill, HttpSession session) throws ServiceException;
}
