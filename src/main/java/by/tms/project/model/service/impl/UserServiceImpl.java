package by.tms.project.model.service.impl;

import by.tms.project.model.dao.impl.UserDaoImpl;
import by.tms.project.model.dao.EntityTransaction;
import by.tms.project.model.entity.Entity;
import by.tms.project.model.entity.Role;
import by.tms.project.model.entity.User;
import by.tms.project.exception.DaoException;
import by.tms.project.exception.ServiceException;
import by.tms.project.model.service.UserService;
import by.tms.project.model.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public Optional<User> findByLogin(String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        EntityTransaction transaction = new EntityTransaction();
        Optional<User> optionalUser;
        try{
            transaction.begin(userDao);
            optionalUser = userDao.findByLogin(login);

        }catch (DaoException e){
            throw new ServiceException("",e);
        }finally {
            try {
                transaction.end();
            }catch (DaoException e){
                logger.error("",e);
            }
        } return optionalUser;
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

    @Override
    public boolean findUserByLoginAndPassword(String login, String password) throws ServiceException {
        return false;
    }

    @Override
    public boolean checkUserLogin(String login) throws ServiceException {
        return false;
    }

    @Override
    public boolean checkOldPassword(User user, String newPassword) throws ServiceException {
         return false;
    }

    @Override
    public long registerUser(String login, String password, Role role) throws ServiceException {
        return 0;
    }

    @Override
    public Optional<User> registerPatient(Map<String, String> parameters) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> registerDoctor(Map<String, String> parameters) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) throws ServiceException {
        return Optional.empty();
    }


}
