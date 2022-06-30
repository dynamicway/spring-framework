package me.spring.security.oauth2

import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class DefaultResourceServerClient(
    private val restTemplate: RestTemplate = RestTemplate()
): ResourceServerClient {

    override fun <T> getAccessToken(
        accessTokenRequest: RequestEntity<Void>,
        accessTokenResponseType: Class<T>
    ) = restTemplate.exchange(accessTokenRequest, accessTokenResponseType).body

    override fun <T> getUserInfo(
        userInfoRequest: RequestEntity<Void>,
        userInfoResponseType: Class<T>
    ) = restTemplate.exchange(userInfoRequest, userInfoResponseType).body
}
