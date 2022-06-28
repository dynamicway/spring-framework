package me.spring.security.oauth2

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.servlet.ServletRequest

@ConfigurationProperties(prefix = "authentication.oauth2")
@ConstructorBinding
class DefaultOauth2AuthenticationCodeFactory(private val clients: HashMap<String, ResourceServer>): Oauth2AuthenticationCodeFactory {

    override fun getOauth2AuthenticationCodeBy(servletRequest: ServletRequest): Oauth2AuthenticationCode {
        servletRequest.getParameter("resourceServer")
        // httpServletRequest 객체를 파싱하여 resourceServer 를 알아내고 그에 맞는 resourceServer 객체를 반환한다.
        TODO()
    }

}
