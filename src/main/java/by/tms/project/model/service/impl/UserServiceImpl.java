package by.tms.project.model.service.impl;

import by.tms.project.model.dao.impl.UserDaoImpl;
import by.tms.project.model.dao.impl.EntityTransaction;
import by.tms.project.model.entity.Entity;
import by.tms.project.model.entity.User;
import by.tms.project.exception.DaoException;
import by.tms.project.exception.ServiceException;
import by.tms.project.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.tms.project.controller.command.RequestParameter.LOGIN;
import static by.tms.project.controller.command.RequestParameter.PASSWORD;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance(){
        return instance;
    }

    @Override
    public List<? extends Entity> findByParameter(String parameter, String parameterValue, String commandName,
                                                  HttpServletRequest request) throws ServiceException {
       //todo
        return null;
    }

    @Override
    public List<? extends Entity> findByParameter(String parameter, String parameterFrom, String parameterTo,
                                                  String commandName, HttpServletRequest request) throws ServiceException {
       //todo
        return null;
    }

    @Override
    public Optional<User> findPatientByLogin(String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        EntityTransaction transaction = new EntityTransaction();
        Optional<User> patient;
        try{
            transaction.begin(userDao);
            patient = userDao.findByLogin(login);

        }catch (DaoException e){
            throw new ServiceException("",e);
        }finally {
            try {
                transaction.end();
            }catch (DaoException e){
                logger.error("",e);
            }
        } return patient;
    }

    @Override
    public boolean createPatient(User user, HttpServletRequest request) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        EntityTransaction transaction = new EntityTransaction();

        try{
            transaction.begin(userDao);
            userDao.create(user);
            transaction.commit();
            userDao.setLogin(user,request.getParameter(LOGIN));
            userDao.setPassword(user,request.getParameter(PASSWORD));
        }catch (DaoException e){
            logger.error("",e);
            throw new ServiceException("",e);
        }
        finally {
            try {
                transaction.end();
            }catch (DaoException e){
                logger.error("",e);
            }
        } return true;
    }

    @Override
    public boolean checkForLogIn(String login, String password) throws ServiceException {
        boolean isApprove = false;
        UserDaoImpl dbUserDao = UserDaoImpl.getInstance();
        EntityTransaction transaction = new EntityTransaction();

        try {
            transaction.beginTransaction(dbUserDao);
            Optional<User> user = dbUserDao.findByLogin(login);
            if(user.isPresent()){
                isApprove = dbUserDao.checkOldPassword(user.get(),password);
            }
        }catch (DaoException e){
            throw new ServiceException("",e);

        }finally {
            try {
                transaction.end();
            }catch (DaoException e){
                logger.error("",e);
            }
        } return isApprove;
    }


}
