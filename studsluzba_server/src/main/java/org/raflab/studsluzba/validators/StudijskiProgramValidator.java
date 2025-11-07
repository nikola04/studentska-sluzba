package org.raflab.studsluzba.validators;

import org.raflab.studsluzba.controllers.request.StudijskiProgramRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StudijskiProgramValidator implements ConstraintValidator<ValidStudijskiProgram, StudijskiProgramRequest> {

    private final List<String> validOznake = Arrays.asList("RN", "RM");
    private final List<String> validVrste = Arrays.asList("OAS", "OSS", "MAS");

    @Override
    public boolean isValid(StudijskiProgramRequest sp, ConstraintValidatorContext context) {
        if (sp == null) return true;

        boolean valid = true;

        if (!validOznake.contains(sp.getOznaka())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Oznaka must be RN or RM")
                    .addPropertyNode("oznaka")
                    .addConstraintViolation();
            valid = false;
        }

        if (!validVrste.contains(sp.getVrstaStudija())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("vrstaStudija must be OAS, OSS or MAS")
                    .addPropertyNode("vrstaStudija")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
