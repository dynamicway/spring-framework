package me.spring.web.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeanValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void product_name_is_not_blank(String blankName) {
        Product product = new Product(
                blankName,
                getValidQuantity(),
                getValidPrice()
        );

        Set<ConstraintViolation<Product>> validationResult = validator.validate(product);

        assertThat(validationResult).hasSize(1);
        ConstraintViolation<Product> productConstraintViolation = validationResult.stream().findFirst().get();
        assertThat(productConstraintViolation.getPropertyPath().toString()).hasToString("name");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    @NullSource
    void product_quantity_ranges_from_0_to_100(Integer invalidQuantity) {
        Product product = new Product(
                getValidName(),
                invalidQuantity,
                getValidPrice()
        );

        Set<ConstraintViolation<Product>> validationResult = validator.validate(product);

        assertThat(validationResult).hasSize(1);
        ConstraintViolation<Product> productConstraintViolation = validationResult.stream().findFirst().get();
        assertThat(productConstraintViolation.getPropertyPath().toString()).hasToString("quantity");
    }

    @ParameterizedTest
    @ValueSource(longs = -1)
    @NullSource
    void product_price_is_not_negative(Long invalidPrice) {
        Product product = new Product(
                getValidName(),
                getValidQuantity(),
                invalidPrice
        );

        Set<ConstraintViolation<Product>> validationResult = validator.validate(product);

        assertThat(validationResult).hasSize(1);
        ConstraintViolation<Product> productConstraintViolation = validationResult.stream().findFirst().get();
        assertThat(productConstraintViolation.getPropertyPath().toString()).hasToString("price");
    }

    private String getValidName() {
        return "productName";
    }

    private long getValidPrice() {
        return 10L;
    }

    private int getValidQuantity() {
        return 10;
    }

}
