package com.etcenteprise.newsoftheearth.validations;

import com.etcenteprise.newsoftheearth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintEmailValidator implements ConstraintValidator<IsValidEmail, String> {

    @Autowired
    private UserService userService;


    @Override
    public void initialize(IsValidEmail isValidEmail) {
        isValidEmail.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        try {
            if (userService.findByEmail(email) != null) {
                return false;
            }
        } catch (NullPointerException e) {
        }
        return true;
    }
}


//    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z]).{10,20})";
//    private Pattern pattern;
//    private Matcher matcher;

//    public PasswordConstraintValidator() {
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//    }


//        if (email == null) {
//            return false;
//        }
//        matcher = pattern.matcher(password);
//       return matcher.matches();