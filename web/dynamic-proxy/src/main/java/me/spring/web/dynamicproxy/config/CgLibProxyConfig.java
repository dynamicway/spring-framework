package me.spring.web.dynamicproxy.config;

import me.spring.web.dynamicproxy.user.UserRepository;
import me.spring.web.dynamicproxy.user.UserServiceImpl;
import me.spring.web.dynamicproxy.proxy.CacheCgLibProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CgLibProxyConfig {

    @Bean
    public UserServiceImpl userService(UserRepository userRepository) {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new CacheCgLibProxy(userService));
        return (UserServiceImpl) enhancer.create();
    }

}
