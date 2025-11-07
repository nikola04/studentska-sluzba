package org.raflab.studsluzba.validators;

import org.raflab.studsluzba.controllers.request.IspitniRokRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, IspitniRokRequest> {

    @Override
    public boolean isValid(IspitniRokRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;

        return request.getKraj().isAfter(request.getPocetak());
    }
}
