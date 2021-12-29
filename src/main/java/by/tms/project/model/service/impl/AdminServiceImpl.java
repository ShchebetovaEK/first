package by.tms.project.model.service.impl;

import by.tms.project.exception.ServiceException;
import by.tms.project.model.entity.Entity;
import by.tms.project.model.entity.User;
import by.tms.project.model.service.AdminUserService;
import by.tms.project.model.util.property.PropertyLoader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

public class AdminServiceImpl implements AdminUserService {
   private static final Logger logger = LogManager.getLogger();
   private static final AdminServiceImpl instance = new AdminServiceImpl();
   private static final String PROPERTY_PATH = "message.properties";
   private static final Properties property = PropertyLoader.getProperty(PROPERTY_PATH);

   private AdminServiceImpl(){

   }

   public static AdminServiceImpl getInstance(){
       return instance;
   }

    @Override
    public void edit(Entity entity, String commandName) throws ServiceException {

    }

    @Override
    public void delete(long id, String commandName) throws ServiceException {

    }

    @Override
    public void createAdmin(User user, HttpServletRequest request) throws ServiceException {

    }

    @Override
    public void closeProtocol(Long protocolId, Long patientId, Long doctorId, BigDecimal protocolCost, HttpSession session) throws ServiceException {

    }

    @Override
    public void createProtocol(Map<String, String> createProtocolMap, HttpSession response) throws ServiceException {

    }
}
