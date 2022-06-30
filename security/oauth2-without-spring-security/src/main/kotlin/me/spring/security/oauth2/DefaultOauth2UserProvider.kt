package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class DefaultOauth2UserProvider(
    private val restTemplate: RestTemplate = RestTemplate()
): Oauth2UserProvider {

    override fun getOauth2User(authenticationCode: Oauth2AuthenticationCode): Oauth2User {
        val accessTokenRequestUri = UriComponentsBuilder.fromUriString(authenticationCode.accessTokenUri)
                .queryParams(authenticationCode.accessTokenParameters)
                .build()
                .toUri()
        val accessTokenRequest = RequestEntity.method(HttpMethod.POST, accessTokenRequestUri)
                .build()
        restTemplate.exchange(accessTokenRequest, Oauth2AccessTokenResponse::class.java).body ?: throw IllegalStateException()

        return Oauth2User()
    }

}
