package me.spring.web.dynamicproxy.config;

import me.spring.web.dynamicproxy.proxy.CachePointcut;
import me.spring.web.dynamicproxy.user.UserRepository;
import me.spring.web.dynamicproxy.user.UserService;
import me.spring.web.dynamicproxy.user.UserServiceImpl;
import me.spring.web.dynamicproxy.proxy.CacheAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        ProxyFactory proxyFactory = new ProxyFactory(userService);
        DefaultPointcutAdvisor cacheAdvisor = new DefaultPointcutAdvisor(new CachePointcut(), new CacheAdvice());
        proxyFactory.addAdvisor(cacheAdvisor);
        return (UserService) proxyFactory.getProxy();
    }

}
