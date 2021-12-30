package by.tms.project.model.service.impl;
import by.tms.project.exception.ServiceException;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientServiceImpl implements PatientService{
   private static final Logger logger = LogManager.getLogger();
   private static final PatientServiceImpl instance =new PatientServiceImpl();


   private PatientServiceImpl(){}


    @Override
    public boolean toUpBalance(String login, long refill, HttpSession session) throws ServiceException {
      return false;
}
}
