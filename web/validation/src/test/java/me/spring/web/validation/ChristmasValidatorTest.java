package me.spring.web.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasValidatorTest {

    private final ChristmasValidator christmasValidator = new ChristmasValidator();

    @Test
    void validation_fails_if_the_date_is_empty() {
        boolean isChristmas = christmasValidator.isValid(null, null);

        assertThat(isChristmas).isFalse();
    }

    @ParameterizedTest
    @EnumSource(names = {"DECEMBER"}, mode = EnumSource.Mode.EXCLUDE)
    void validation_fails_if_the_date_is_not_december(Month givenMonth) {
        LocalDate givenDate = LocalDate.of(2022, givenMonth, getDateOfChristmas());

        boolean isChristmas = christmasValidator.isValid(givenDate, null);

        assertThat(isChristmas).isFalse();
    }

    @ParameterizedTest
    @ArgumentsSource(DatesWithout25Provider.class)
    void validation_fails_if_the_date_is_not_25th(int givenMonthOfDate) {
        LocalDate givenDate = LocalDate.of(2022, getMonthOfChristmas(), givenMonthOfDate);

        boolean isChristmas = christmasValidator.isValid(givenDate, null);

        assertThat(isChristmas).isFalse();
    }

    @Test
    void validation_successes_if_the_date_is_december_25th() {
        LocalDate givenDate = LocalDate.of(2022, getMonthOfChristmas(), getDateOfChristmas());

        boolean isChristmas = christmasValidator.isValid(givenDate, null);

        assertThat(isChristmas).isTrue();
    }

    private Month getMonthOfChristmas() {
        return Month.DECEMBER;
    }

    private int getDateOfChristmas() {
        return 25;
    }

    private static class DatesWithout25Provider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.iterate(1, date -> date + 1).filter(date -> date != 25).limit(30).map(Arguments::arguments);
        }
    }

}
