package com.vm.test.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author huaihai.guan
 * @since 2021/8/23 16:13
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private String message;


    @Override
    public void initialize(Phone phone) {
        this.message = phone.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("(\\d+)+");
        if (!StringUtils.isEmpty(value) && value.length() == 11 && value.startsWith("1") && pattern.matcher(value).find()) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(this.message).addConstraintViolation();
        return false;
    }
}
