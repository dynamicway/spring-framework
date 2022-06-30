package me.spring.security.oauth2

import org.springframework.http.RequestEntity

interface ResourceServerClient {

    fun <T> getAccessToken(
        accessTokenRequest: RequestEntity<Void>,
        accessTokenResponseType: Class<T>
    ): T?

    fun <T> getUserInfo(
        userInfoRequest: RequestEntity<Void>,
        userInfoResponseType: Class<T>
    ): T?

}
