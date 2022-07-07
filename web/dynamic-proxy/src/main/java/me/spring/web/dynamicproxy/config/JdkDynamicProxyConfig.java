package me.spring.web.dynamicproxy.config;

import me.spring.web.dynamicproxy.user.UserRepository;
import me.spring.web.dynamicproxy.user.UserService;
import me.spring.web.dynamicproxy.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdkDynamicProxyConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
