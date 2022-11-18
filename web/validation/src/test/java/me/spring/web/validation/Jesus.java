package me.spring.web.validation;

import java.time.LocalDate;

record Jesus(
        @Christmas
        LocalDate birth
) {}
