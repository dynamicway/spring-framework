package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod

internal class ResourceServerTest {

    @Test
    fun accessTokenRequest() {
        val accessTokenRequestHttpMethod = HttpMethod.PATCH
        val accessTokenRequestUri = "https://userInfoUri"
        val grantType = "grantType"
        val clientId = "clientId"
        val clientSecret = "clientSecret"
        val clientName = "clientName"
        val resourceServer = getResourceServer(
            accessTokenRequestHttpMethod = accessTokenRequestHttpMethod,
            accessTokenRequestUri = accessTokenRequestUri,
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret,
            clientName = clientName
        )
        val expectedAccessTokenRequest = AccessTokenRequest(
            httpMethod = accessTokenRequestHttpMethod,
            uri = accessTokenRequestUri,
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

    @Test
    fun userInfoRequest() {
        val userInfoRequestHttpMethod = HttpMethod.GET
        val userInfoRequestUri = "https//userInfo"
        val resourceServer = getResourceServer(
            userInfoRequestHttpMethod = userInfoRequestHttpMethod,
            userInfoRequestUri = userInfoRequestUri
        )
        val expectedUserInfoRequest = UserInfoRequest(
            httpMethod = userInfoRequestHttpMethod,
            uri = userInfoRequestUri
        )

        val userInfoRequest = resourceServer.userInfoRequest

        assertThat(userInfoRequest).isEqualTo(expectedUserInfoRequest)
    }

}
