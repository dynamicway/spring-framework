package me.spring.security.oauth2

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest

@ConfigurationProperties(prefix = "authentication.oauth2")
@ConstructorBinding
class DefaultResourceServerRequestFactory(private val clients: HashMap<String, ResourceServer>): ResourceServerRequestFactory {

    override fun getResourceServerRequestBy(servletRequest: ServletRequest): ResourceServerRequest {
        val httpServletRequest = servletRequest as HttpServletRequest
        val resourceServerName = httpServletRequest.requestURI.substringAfterLast("auth/")
        val authenticationCode = httpServletRequest.getParameter("code")
        val resourceServer = clients[resourceServerName] ?: throw IllegalArgumentException("not supported resource server")

        return ResourceServerRequest(
            resourceServerName = resourceServerName,
            accessTokenUri = resourceServer.accessTokenUri,
            accessTokenParameters = resourceServer.accessTokenParameter.apply { add("code", authenticationCode) },
            userInfoHttpMethod = resourceServer.userInfoHttpMethod,
            userInfoUri = resourceServer.userInfoUri,
            userInfoAttributes = resourceServer.userInfoAttributes.attributes
        )
    }

}
