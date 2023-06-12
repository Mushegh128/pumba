package com.automobile.pumba.data.transfer.validation;

import com.automobile.pumba.data.transfer.validation.impl.MaxCurrentYearValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MaxCurrentYearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxCurrentYear {

    String message() default "Invalid year";
}
