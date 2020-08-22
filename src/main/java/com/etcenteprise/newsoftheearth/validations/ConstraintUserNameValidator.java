package com.etcenteprise.newsoftheearth.validations;

import com.etcenteprise.newsoftheearth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConstraintUserNameValidator implements ConstraintValidator<IsValidUserName, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsValidUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {

        try {
            if (userService.findByUsername(userName).isPresent() == true) {
                return false;
            }
        } catch (NullPointerException e) {
        }
        return true;
    }
}
