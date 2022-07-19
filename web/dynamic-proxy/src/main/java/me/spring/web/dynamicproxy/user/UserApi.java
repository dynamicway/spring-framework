package me.spring.web.dynamicproxy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    String getUserName(@PathVariable long userId) {
        return userService.getUserName(userId);
    }

}
