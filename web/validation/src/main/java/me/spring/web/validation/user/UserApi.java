package me.spring.web.validation.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/by-Valid-annotation-on-controller-method")
    public void byValidAnnotationOnControllerMethod(@Valid @RequestBody User user) {

    }

    @GetMapping("/age-must-not-be-null")
    public void ageMustNotBeNull(@NotNull Integer age) {

    }

    @PostMapping("/by-service-hierarchy")
    public void byServiceHierarchy(@RequestBody User user) {
        userService.validate(user);
    }


}
