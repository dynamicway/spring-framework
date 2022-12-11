package me.spring.web.validation.user;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;

public record User(
        @NotNull
        String id,
        @Negative
        int age
) {
}
