package com.etcenteprise.newsoftheearth.validations;

import com.etcenteprise.newsoftheearth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<IsValidPassword, String> {

    @Autowired
    private UserRepository userRepository;

//    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z]).{10,20})";
//    private Pattern pattern;
//    private Matcher matcher;

//    public PasswordConstraintValidator() {
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//    }

    @Override
    public void initialize(IsValidPassword isValidPassword) {
        isValidPassword.message();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
//
//        if(userRepository.findByEmail(password).isPresent()){
//            return false;
//        }
        System.out.println(userRepository.findByEmail(password).isPresent());

        if (password == null) {
            return false;
        }
//        matcher = pattern.matcher(password);
 //       return matcher.matches();
        return  true;
    }
}
