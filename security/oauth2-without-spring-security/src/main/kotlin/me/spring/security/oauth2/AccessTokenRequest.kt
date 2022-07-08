package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.util.UriComponentsBuilder

data class AccessTokenRequest(
    private val httpMethod: HttpMethod,
    private val uri: String,
    private val accessTokenRequestParameters: AccessTokenRequestParameters
) {

    val accessTokenRequestEntity by lazy {
        val accessTokenUri = UriComponentsBuilder.fromUriString(uri)
                .queryParams(accessTokenRequestParameters.parameters)
                .build()
                .toUri()

        RequestEntity.method(httpMethod, accessTokenUri)
                .build()
    }

}
