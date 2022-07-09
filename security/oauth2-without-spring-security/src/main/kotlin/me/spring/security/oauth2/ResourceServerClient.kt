package me.spring.security.oauth2

import org.springframework.http.RequestEntity

interface ResourceServerClient {

    fun getAccessToken(
        accessTokenRequest: RequestEntity<Void>
    ): AccessTokenResponse?

    fun getUserInfo(
        userInfoRequest: RequestEntity<Void>
    ): UserInfoResponse?

}
