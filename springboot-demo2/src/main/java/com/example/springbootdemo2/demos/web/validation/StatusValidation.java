package com.example.springbootdemo2.demos.web.validation;

import com.example.springbootdemo2.demos.web.anno.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidation implements ConstraintValidator<Status, String> {
    /**
     *
     * @param string    // 将来需要校验的数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string == null) {
            return false;
        }
        if (string.equals("0") || string.equals("1")) {
            return true;
        }
        return false;
    }
}
