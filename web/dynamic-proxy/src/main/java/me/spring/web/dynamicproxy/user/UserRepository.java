package me.spring.web.dynamicproxy.user;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User getUserById(Long userId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new User(userId, "user" + userId);
    }

}
