package me.spring.security.oauth2

import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class DefaultResourceServerClient(
    private val restTemplate: RestTemplate = RestTemplate()
): ResourceServerClient {

    override fun getAccessToken(
        accessTokenRequest: RequestEntity<Void>
    ) = restTemplate.exchange(accessTokenRequest, AccessTokenResponse::class.java).body

    override fun getUserInfo(
        userInfoRequest: RequestEntity<Void>
    ) = restTemplate.exchange(userInfoRequest, UserInfoResponse::class.java).body
}
