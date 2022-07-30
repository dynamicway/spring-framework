package me.spring.security.oauth2

import javax.servlet.ServletRequest

class SpyResourceServerRequestFactory: ResourceServerRequestFactory {

    override fun getResourceServerRequestBy(servletRequest: ServletRequest): ResourceServerRequest {
        TODO("Not yet implemented")
    }
}
