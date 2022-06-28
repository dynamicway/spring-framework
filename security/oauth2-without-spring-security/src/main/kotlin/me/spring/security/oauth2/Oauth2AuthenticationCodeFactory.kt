package me.spring.security.oauth2

import javax.servlet.ServletRequest

interface Oauth2AuthenticationCodeFactory {

    fun getOauth2AuthenticationCodeBy(servletRequest: ServletRequest): Oauth2AuthenticationCode

}
