package org.raflab.studsluzba.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudijskiProgramValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStudijskiProgram {
    String message() default "Invalid oznaka or vrstaStudija";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
