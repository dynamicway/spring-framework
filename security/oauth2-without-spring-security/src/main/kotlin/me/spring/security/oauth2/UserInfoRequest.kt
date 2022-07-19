package me.spring.security.oauth2

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity

data class UserInfoRequest(
    private val httpMethod: HttpMethod,
    private val uri: String
) {
    fun getAccessTokenRequestEntity(accessToken: String): RequestEntity<Void> {
        return RequestEntity.method(httpMethod, uri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .build()
    }
}
