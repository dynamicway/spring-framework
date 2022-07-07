package me.spring.web.dynamicproxy;

import me.spring.web.dynamicproxy.config.CgLibProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import({JdkDynamicProxyConfig.class})
@Import({CgLibProxyConfig.class})
@SpringBootApplication(scanBasePackages = "me.spring.web.dynamicproxy.user")
public class DynamicProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicProxyApplication.class, args);
    }

}
