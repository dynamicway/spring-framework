package me.spring.web.validation.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserApi {

    @PostMapping("/by-Valid-annotation-on-controller-method")
    public void byValidAnnotationOnControllerMethod(@Valid @RequestBody User user) {

    }

}
