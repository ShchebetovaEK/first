package by.tms.project.model.validator;

import by.tms.project.exception.DaoException;
import by.tms.project.exception.ServiceException;
import by.tms.project.model.entity.User;
import by.tms.project.model.util.property.PropertyLoader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Properties;

import static by.tms.project.controller.command.RequestParameter.*;
import static by.tms.project.model.service.MessageAttributeName.*;
import static by.tms.project.model.service.MessageAttributeValue.*;

public class SignUpValidator {
    private static final DataValidator DATA_VALIDATOR = DataValidator.getInstance();
    private static final String PROPERTY_PATH = "message.properties";
    private static final Properties property = PropertyLoader.getProperty(PROPERTY_PATH);
    private static SignUpValidator instance;

    private SignUpValidator() {
    }

    public static SignUpValidator getInstance() {
        if (instance == null) {
            instance = new SignUpValidator();
        }

        return instance;
    }

    public boolean isFormSignUpFormValid(User userForm, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int counter = 0;
        String login = userForm.getLogin();
        if (!DATA_VALIDATOR.isLoginValid(login)) {
            userForm.setLogin(EMPTY_STRING);
            session.setAttribute(PASSWORD, EMPTY_STRING);
            session.setAttribute(CONFIRMED_PASSWORD, EMPTY_STRING);
            session.setAttribute(INCORRECT_LOGIN, property.getProperty(MESSAGE_INCORRECT_LOGIN));
            ++counter;
        }

        if (!DATA_VALIDATOR.isPasswordValid(request.getParameter(PASSWORD))) {
            session.setAttribute(PASSWORD, EMPTY_STRING);
            session.setAttribute(CONFIRMED_PASSWORD, EMPTY_STRING);
            session.setAttribute(INCORRECT_PASSWORD, property.getProperty(MESSAGE_INCORRECT_PASSWORD));
            ++counter;
        } else if (!request.getParameter(CONFIRMED_PASSWORD).equals(request.getParameter(PASSWORD))) {
            session.setAttribute(PASSWORD, EMPTY_STRING);
            session.setAttribute(CONFIRMED_PASSWORD, EMPTY_STRING);
            session.setAttribute(NOT_SAME_PASSWORD, property.getProperty(MESSAGE_NOT_SAME_PASSWORD));
            ++counter;
        }

        String email = userForm.getEmail();
        if (!DATA_VALIDATOR.isEmailValid(email)) {
            userForm.setEmail(EMPTY_STRING);
            session.setAttribute(PASSWORD, EMPTY_STRING);
            session.setAttribute(CONFIRMED_PASSWORD, EMPTY_STRING);
            session.setAttribute(INCORRECT_EMAIL, property.getProperty(MESSAGE_INCORRECT_EMAIL));
            ++counter;
        }

        String phoneNumber = userForm.getPhoneNumber();
        if (!DATA_VALIDATOR.isPhoneNumberValid(phoneNumber)) {
            userForm.setPhoneNumber(EMPTY_STRING);
            session.setAttribute(PASSWORD, EMPTY_STRING);
            session.setAttribute(CONFIRMED_PASSWORD, EMPTY_STRING);
            session.setAttribute(INCORRECT_PHONE_NUMBER, property.getProperty(MESSAGE_INCORRECT_PHONE_NUMBER));
            ++counter;
        }

        if(counter == 0){
            try {
                List<String> usingData = DATA_VALIDATOR.checkPresentData(userForm.getLogin(),
                        userForm.getEmail(), userForm.getPhoneNumber());
                if(!usingData.isEmpty()){
                    String message = DATA_VALIDATOR.getUsingElement(usingData);
                    DATA_VALIDATOR.defineMessageForUsingData(request, message);
                    counter++;
                }
            } catch (DaoException e) {
                throw new ServiceException("Login and(or) e-mail and(or) phone number is(are) already using", e);
            }
        }

        return counter == 0;
    }

}
