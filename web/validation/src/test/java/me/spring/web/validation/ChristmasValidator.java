package me.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class ChristmasValidator implements ConstraintValidator<Christmas, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (Objects.isNull(date))
            return false;
        return isChristmas(date);
    }

    private boolean isChristmas(LocalDate date) {
        return date.getMonth().equals(Month.DECEMBER) && date.getDayOfMonth() == 25;
    }

}
