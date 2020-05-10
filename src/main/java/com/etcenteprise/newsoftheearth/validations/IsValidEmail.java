package com.etcenteprise.newsoftheearth.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConstraintEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidEmail {
    String message() default "Email Already Exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}