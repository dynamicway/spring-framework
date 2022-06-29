package me.spring.security.oauth2

import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class DefaultOauth2UserProvider(
    private val restTemplate: RestTemplate = RestTemplate()
): Oauth2UserProvider {

    override fun getOauth2User(authenticationCode: Oauth2AuthenticationCode): Oauth2User {
        val accessTokenRequestParameters = LinkedMultiValueMap<String, String>()
        authenticationCode.accessTokenParameters.forEach { accessTokenRequestParameters.add(it.key, it.value) }
        val accessTokenRequestUri = UriComponentsBuilder.fromUriString(authenticationCode.accessTokenUri)
                .queryParams(accessTokenRequestParameters)
                .build()
                .toUri()
        restTemplate.getForEntity(accessTokenRequestUri, Oauth2AccessTokenResponse::class.java)

        return Oauth2User()
    }

}
