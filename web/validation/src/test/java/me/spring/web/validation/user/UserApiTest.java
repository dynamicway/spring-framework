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
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void validate_query_parameters() {
        RestAssured
                .when()
                .get("/age-must-not-be-null")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void validate_by_Service_hierarchy() {
        RestAssured
                .given()
                .body("{}")
                .contentType(ContentType.JSON)
                .when()
                .post("/by-service-hierarchy")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
