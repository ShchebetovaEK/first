package by.tms.project.model.validator;

import by.tms.project.model.util.property.PropertyLoader;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Properties;

public class AdminValidator {
    private static final String PROPERTY_PATH = "message.properties";
    private static final Properties property = PropertyLoader.getProperty(PROPERTY_PATH);
    private static AdminValidator instanse;

    private AdminValidator() {
    }

    public static AdminValidator getInstance() {
        if (instanse == null) {
            instanse = new AdminValidator();

        }
        return instanse;
    }

    public boolean isCreateProtocolFormValid(Map<String, String> formData, HttpSession session) {
        return true;
    }
}
