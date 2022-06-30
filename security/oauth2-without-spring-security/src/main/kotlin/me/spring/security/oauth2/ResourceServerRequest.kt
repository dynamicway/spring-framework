package me.spring.security.oauth2

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.util.UriComponentsBuilder

class ResourceServerRequest(
    private val accessTokenUri: String,
    private val accessTokenParameters: MultiValueMap<String, String>,
    private val userInfoHttpMethod: HttpMethod,
    private val userInfoUri: String,
    private val userInfoAttributes: Map<String, String>
) {

    fun getAccessTokenRequestEntity(): RequestEntity<Void> {
        val uri = UriComponentsBuilder.fromUriString(accessTokenUri)
                .queryParams(accessTokenParameters)
                .build()
                .toUri()
        return RequestEntity.method(userInfoHttpMethod, uri)
                .build()
    }

    fun getUserInfoRequestEntity(accessToken: String): RequestEntity<Void> {
        val uri = UriComponentsBuilder.fromUriString(userInfoUri)
                .build()
                .toUri()

        return RequestEntity.method(userInfoHttpMethod, uri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .build()
    }

}
