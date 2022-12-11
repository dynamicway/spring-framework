package me.spring.web.validation.user;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserApiTest {

    @Test
    void validate_by_Valid_annotation() {
        RestAssured
                .given()
                .body("""
                        {"age": 1}
                        """)
                .contentType(ContentType.JSON)
                .when()
                .post("/by-Valid-annotation-on-controller-method")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
