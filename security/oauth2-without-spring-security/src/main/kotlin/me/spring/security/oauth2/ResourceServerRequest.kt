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

    val getUserInfoRequestType = Map::class.java
    val getAccessTokenResponseType = Oauth2AccessTokenResponse::class.java

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

    fun getUserAttributes(getUserInfoResponse: Map<String, *>): HashMap<String, String> {
        val userAttributes = hashMapOf<String, String>()
        userInfoAttributes.forEach { userAttributes[it.key] = getUserInfoResponse[it.value] as String }
        return userAttributes
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceServerRequest

        if (accessTokenUri != other.accessTokenUri) return false
        if (accessTokenParameters != other.accessTokenParameters) return false
        if (userInfoHttpMethod != other.userInfoHttpMethod) return false
        if (userInfoUri != other.userInfoUri) return false
        if (userInfoAttributes != other.userInfoAttributes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accessTokenUri.hashCode()
        result = 31 * result + accessTokenParameters.hashCode()
        result = 31 * result + userInfoHttpMethod.hashCode()
        result = 31 * result + userInfoUri.hashCode()
        result = 31 * result + userInfoAttributes.hashCode()
        return result
    }

}
