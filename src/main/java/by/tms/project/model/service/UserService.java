package by.tms.project.model.service;

import by.tms.project.model.entity.Entity;
import by.tms.project.model.entity.Role;
import by.tms.project.model.entity.User;
import by.tms.project.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<? extends Entity> findByParameter(String parameter, String parameterValue, String commandName, HttpServletRequest request) throws ServiceException;

    List<? extends Entity> findByParameter(String parameter, String parameterFrom, String parameterTo, String commandName, HttpServletRequest request) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    boolean createPatient(User userForm, HttpServletRequest request) throws ServiceException;

    boolean checkForLogIn(String login,String password) throws ServiceException;

 //   boolean findUserByLoginAndPassword( String login,String  password) throws ServiceException;

    boolean checkUserLogin (String login) throws ServiceException;

    boolean checkOldPassword(User user, String newPassword) throws ServiceException;

    long registerUser (String login, String password, Role role) throws ServiceException;

    Optional<User> registerPatient (Map<String,String > parameters) throws ServiceException;

    Optional<User> registerDoctor (Map<String,String> parameters) throws ServiceException;

    Optional<User> findById(Long id) throws ServiceException;

    Optional<User> findUserByLoginAndPassword( String login,String  password) throws ServiceException;


}
