package me.spring.web.dynamicproxy;

import me.spring.web.dynamicproxy.config.ProxyFactoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
//        CgLibProxyConfig.class
//        JdkDynamicProxyConfig.class
        ProxyFactoryConfig.class
})
@SpringBootApplication(scanBasePackages = "me.spring.web.dynamicproxy.user")
public class DynamicProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicProxyApplication.class, args);
    }

}
