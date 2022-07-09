package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.util.UriComponentsBuilder

internal class AccessTokenRequestTest {

    @Test
    fun accessTokenRequestEntity() {
        val httpMethod = HttpMethod.PATCH
        val uri = "https//accessTokenUri"
        val accessTokenRequestParameters = getAccessTokenRequestParameters()
        val accessTokenRequest = getAccessTokenRequest(
            httpMethod = httpMethod,
            uri = uri,
            accessTokenRequestParameters = accessTokenRequestParameters
        )
        val authenticationCode = "authenticationCode"
        val expectedUri = UriComponentsBuilder.fromUriString(uri)
                .queryParams(accessTokenRequestParameters.parameters)
                .build()
                .toUri()
        val expectedAccessTokenRequestEntity = RequestEntity.method(httpMethod, expectedUri)
                .build()
        val accessTokenRequestEntity = accessTokenRequest.accessTokenRequestEntity

        assertThat(accessTokenRequestEntity).isEqualTo(expectedAccessTokenRequestEntity)
    }

}
