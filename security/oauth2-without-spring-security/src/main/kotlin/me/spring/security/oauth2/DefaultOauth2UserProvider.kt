package me.spring.security.oauth2

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class DefaultOauth2UserProvider(
    private val restTemplate: RestTemplate = RestTemplate()
): Oauth2UserProvider {

    override fun getOauth2User(resourceServerRequest: ResourceServerRequest): Oauth2User {
        val oauth2AccessTokenResponse = restTemplate.exchange(resourceServerRequest.getAccessTokenRequestEntity(), resourceServerRequest.getAccessTokenResponseType).body ?: throw IllegalStateException()
        restTemplate.exchange(resourceServerRequest.getUserInfoRequestEntity(oauth2AccessTokenResponse.accessToken), Map::class.java)


        return Oauth2User()
    }

}
