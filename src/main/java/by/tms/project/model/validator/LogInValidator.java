package by.tms.project.model.validator;

import by.tms.project.model.util.PropertyLoader;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Properties;

import static by.tms.project.controller.command.RequestParameter.*;
import static by.tms.project.model.service.MessageAttributeName.LOG_IN_ERROR;
import static by.tms.project.model.service.MessageAttributeValue.MESSAGE_LOG_IN_ERROR;

public final  class LogInValidator {
    private static final DataValidator DATA_VALIDATOR = DataValidator.getInstance();
    private static final String PROPERTY_PATH = "message.properties";
    private static final Properties property = PropertyLoader.getProperty(PROPERTY_PATH);
    private static LogInValidator instance;

    private LogInValidator(){}

    public static LogInValidator getInstance(){
        if (instance == null){
            instance = new LogInValidator();
        }
        return instance;
    }

    public boolean isFormValid(Map<String,String> fromDate, HttpServletRequest request){
        int counter = 0;
        String login = fromDate.get(LOGIN);
        String password = fromDate.get(PASSWORD);
        if(!DATA_VALIDATOR.isLoginValid(login) || !DATA_VALIDATOR.isPasswordValid(password)){
        fromDate.put(PASSWORD,EMPTY_STRING);
        request.setAttribute(LOG_IN_ERROR,property.getProperty(MESSAGE_LOG_IN_ERROR));
        ++counter;
}
return counter ==0;
    }

}
