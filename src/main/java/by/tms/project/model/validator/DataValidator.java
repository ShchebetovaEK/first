package by.tms.project.model.validator;

public class DataValidator {
    private static final String LOGIN_REGEX = "^(\\w)[\\w_-]{1,18}(\\w)$";
    private static final String PASSWORD_REGEX = "^(.{8,40})$";

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


}
