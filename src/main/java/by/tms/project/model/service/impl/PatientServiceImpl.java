package by.tms.project.model.service.impl;
import by.tms.project.exception.ServiceException;

import by.tms.project.model.entity.Patient;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientServiceImpl implements PatientService{
   private static final Logger logger = LogManager.getLogger();
   private static  PatientServiceImpl instance;
//           =new PatientServiceImpl();


   private PatientServiceImpl(){}

    public static PatientService getInstance() {
       if(instance ==null){
           instance = new PatientServiceImpl();

       }return instance;
    }


    @Override
    public boolean toUpBalance(String login, long refill, HttpSession session) throws ServiceException {
      return false;
}

    @Override
    public Patient findById(long id) {
        return null;
    }
}
