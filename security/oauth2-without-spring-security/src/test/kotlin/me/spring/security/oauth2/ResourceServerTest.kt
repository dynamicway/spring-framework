package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

internal class ResourceServerTest {

    @Test
    fun accessTokenRequest() {
        val userInfoHttpMethod = HttpMethod.PATCH
        val userInfoUri = "https://userInfoUri"
        val grantType = "grantType"
        val clientId = "clientId"
        val clientSecret = "clientSecret"
        val clientName = "clientName"
        val resourceServer = getResourceServer(
            userInfoHttpMethod = userInfoHttpMethod,
            userInfoUri = userInfoUri,
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret,
            clientName = clientName
        )
        val expectedAccessTokenRequest = AccessTokenRequest(
            httpMethod = userInfoHttpMethod,
            uri = userInfoUri,
            accessTokenRequestParameters = AccessTokenRequestParameters(
                grantType = grantType,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = "http://localhost:8080/auth/$clientName"
            )
        )
        val accessTokenRequest = resourceServer.accessTokenRequest

        assertThat(accessTokenRequest).isEqualTo(expectedAccessTokenRequest)
    }

}
