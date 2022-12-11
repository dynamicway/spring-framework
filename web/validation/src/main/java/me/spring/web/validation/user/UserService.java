package me.spring.web.validation.user;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UserService {

    public void validate(@Valid User user) {

    }

}
