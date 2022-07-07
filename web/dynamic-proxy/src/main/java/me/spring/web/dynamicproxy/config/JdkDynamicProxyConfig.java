package me.spring.web.dynamicproxy.config;

import me.spring.web.dynamicproxy.user.UserRepository;
import me.spring.web.dynamicproxy.user.UserService;
import me.spring.web.dynamicproxy.user.UserServiceImpl;
import me.spring.web.dynamicproxy.user.proxy.UserServiceJdkDynamicProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class JdkDynamicProxyConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        return (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, new UserServiceJdkDynamicProxy(userService));
    }

}
