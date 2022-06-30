package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class ResourceServer(
    val clientName: String,
    val clientId: String,
    val clientSecret: String,
    val resourceServerId: String,
    val accessTokenUri: String,
    private val additionalAccessTokenRequestParameters: Map<String, String> = hashMapOf(),
    val userInfoHttpMethod: HttpMethod,
    val userInfoUri: String,
    val userInfoAttributes: UserInfoAttributes
) {

    val accessTokenParameter: MultiValueMap<String, String> by lazy {
        LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("redirect_uri", "http://localhost:8080/auth/$clientName")
            additionalAccessTokenRequestParameters.forEach { add(it.key, it.value) }
        }
    }

    class UserInfoAttributes(
        val profileImage: String,
        val email: String
    ) {

        val attributes by lazy {
            mapOf(
                "profileImage" to profileImage,
                "email" to email
            )
        }

    }

}
