package me.spring.security.oauth2

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = [Oauth2AuthenticationCodeFactory::class])
class SecurityConfig {
}
