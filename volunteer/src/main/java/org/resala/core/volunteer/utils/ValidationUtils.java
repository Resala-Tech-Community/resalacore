package org.resala.core.volunteer.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

public class ValidationUtils {

    public static <T extends Serializable> Set<ConstraintViolation<T>> getConstraintViolations(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(object);
    }

}
