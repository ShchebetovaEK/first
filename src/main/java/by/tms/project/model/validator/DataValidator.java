package by.tms.project.model.validator;

import by.tms.project.controller.command.RequestParameter;
import by.tms.project.exception.DaoException;
import by.tms.project.model.dao.EntityTransaction;
import by.tms.project.model.dao.impl.UserDaoImpl;
import by.tms.project.model.util.property.PropertyLoader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static by.tms.project.controller.command.RequestParameter.*;
import static by.tms.project.controller.command.SessionAttribute.EMAIL;
import static by.tms.project.controller.command.SessionAttribute.PHONE_NUMBER;
import static by.tms.project.model.service.MessageAttributeValue.*;

public class DataValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String PROPERTY_PATH = "message.properties";
    private static final Properties property = PropertyLoader.getProperty(PROPERTY_PATH);
    private static final String LOGIN_REGEX = "^(\\w)[\\w_-]{1,18}(\\w)$";
    private static final String PASSWORD_REGEX = "^(.{8,40})$";
    private static final String EMAIL_REGEX = "^((\\w|[-+])+(\\.(\\w|[-+])*)*@[a-z]+\\.[a-z]+)$";
    private static final String PHONE_NUMBER_REGEX = "^((\\+375|80)(25|29|33|44)\\d{7})$";
    private static final String COMMA = ", ";
    private static final String WHITESPACE = " ";
    private static DataValidator instance;

    private DataValidator(){}

    public static DataValidator getInstance(){
        if(instance == null){
            instance = new DataValidator();
        }
        return instance;
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean isLoginValid(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public boolean isEmailValid(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_REGEX);
    }
    public List<String> checkPresentData(String login, String email, String phoneNumber) throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        List<String> listOfUsing = new ArrayList<>();

        transaction.begin(userDao);
        try {
            if (userDao.findByLogin(login).isPresent()) {
                listOfUsing.add(RequestParameter.LOGIN);
            }
            if (userDao.findByEmail(email).isPresent()) {
                listOfUsing.add(EMAIL);
            }
            if (userDao.findByPhoneNumber(phoneNumber).isPresent()) {
                listOfUsing.add(PHONE_NUMBER);
            }
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("End transaction has been failed", e);
            }
        }
        return listOfUsing;
    }

    public String getUsingElement(List<String> listOfUsing) {
        StringBuilder message = new StringBuilder();
        String messageString;
        for(int i = 0; i < listOfUsing.size(); ++i) {
            if (i < listOfUsing.size() - 1) {
                message.append(listOfUsing.get(i)).append(COMMA);
            } else {
                message.append(listOfUsing.get(i)).append(WHITESPACE);
            }
        }
        String firstLetter = message.substring(0, 1);
        String firstLetterUpper = firstLetter.toUpperCase();
        String other = message.substring(1);
        messageString = firstLetterUpper + other;
        return messageString;
    }

    public void defineMessageForUsingData(HttpServletRequest request, String message){
        if(message.contains(ADMIN_LOGIN_EMAIL_NUMBER)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_LOGIN_EMAIL_PHONE));
        }else if(message.contains(ADMIN_LOGIN_NUMBER)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_LOGIN_PHONE));
        }else if(message.contains(ADMIN_LOGIN_EMAIL)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_LOGIN_EMAIL));
        }else if(message.contains(ADMIN_EMAIL_NUMBER)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_EMAIL_PHONE));
        }else if(message.contains(ADMIN_LOGIN)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_LOGIN));
        }else if(message.contains(ADMIN_EMAIL)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_EMAIL));
        }else if(message.contains(ADMIN_NUMBER)){
            request.setAttribute(MESSAGE_FOR_USING_DATA, property.getProperty(MESSAGE_ADMIN_USING_PHONE));
        }
    }


}
