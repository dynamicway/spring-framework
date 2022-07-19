package me.spring.security.oauth2

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class Oauth2AuthenticationCodeFilter(
    private val resourceServerRequestFactory: ResourceServerRequestFactory,
    private val oauth2UserProvider: Oauth2UserProvider
): Filter {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        println("filter is executed")
        val oauth2AuthenticationCodeBy = resourceServerRequestFactory.getResourceServerRequestBy(request)
        val oauth2User = oauth2UserProvider.getOauth2User(oauth2AuthenticationCodeBy)
        println(oauth2User)
    }

}
