package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity

internal class UserInfoRequestTest {

    @Test
    fun accessTokenRequestEntity() {
        val httpMethod = HttpMethod.POST
        val uri = "https://uri"
        val userInfoRequest = getUserInfoRequest(
            httpMethod = httpMethod,
            uri = uri
        )
        val accessToken = "accessToken"
        val accessTokenRequestEntity = userInfoRequest.getAccessTokenRequestEntity(accessToken)
        val expectedAccessTokenRequestEntity = RequestEntity.method(httpMethod, uri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .build()
        assertThat(accessTokenRequestEntity).isEqualTo(expectedAccessTokenRequestEntity)
    }

}
