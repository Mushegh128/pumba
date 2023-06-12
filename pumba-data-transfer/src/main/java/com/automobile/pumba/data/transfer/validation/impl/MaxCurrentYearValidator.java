package com.automobile.pumba.data.transfer.validation.impl;

import com.automobile.pumba.data.transfer.validation.MaxCurrentYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class MaxCurrentYearValidator implements ConstraintValidator<MaxCurrentYear, Integer> {

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        int currentYear = LocalDateTime.now().getYear();
        return year == null || year <= currentYear;
    }
}
