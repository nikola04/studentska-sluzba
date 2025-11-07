package org.raflab.studsluzba.validators;

import org.raflab.studsluzba.controllers.request.StudijskiProgramRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StudijskiProgramValidator implements ConstraintValidator<ValidStudijskiProgram, StudijskiProgramRequest> {

    private final List<String> validOznake = Arrays.asList("RN", "RI", "SI", "MD");

    @Override
    public boolean isValid(StudijskiProgramRequest sp, ConstraintValidatorContext context) {
        if (sp == null) return true;

        boolean valid = true;

        if (!validOznake.contains(sp.getOznaka())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Oznaka must be RN, RI, SI or MD")
                    .addPropertyNode("oznaka")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
