package me.spring.security.oauth2

import javax.servlet.ServletRequest

interface ResourceServerRequestFactory {

    fun getResourceServerRequestBy(servletRequest: ServletRequest): ResourceServerRequest

}
