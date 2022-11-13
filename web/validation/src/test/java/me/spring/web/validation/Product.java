package me.spring.web.validation;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

record Product(
        @NotBlank
        String name,
        @NotNull
        @Range(min = 0, max = 100)
        Integer quantity,
        @NotNull
        @PositiveOrZero
        Long price
) {
}
