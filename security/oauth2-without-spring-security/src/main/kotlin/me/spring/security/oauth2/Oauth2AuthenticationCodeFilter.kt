package me.spring.security.oauth2

import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
class Oauth2AuthenticationCodeFilter(
    private val oauth2AuthenticationCodeFactory: Oauth2AuthenticationCodeFactory,
    private val oauth2UserProvider: Oauth2UserProvider
): Filter {

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val oauth2AuthenticationCodeBy = oauth2AuthenticationCodeFactory.getOauth2AuthenticationCodeBy(request)
        oauth2UserProvider.getOauth2User(oauth2AuthenticationCodeBy)
    }

}
