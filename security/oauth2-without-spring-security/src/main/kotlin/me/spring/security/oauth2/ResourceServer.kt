package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class ResourceServer(
    val clientName: String,
    val clientId: String,
    val clientSecret: String,
    val accessTokenRequestUri: String,
    val accessTokenRequestHttpMethod: HttpMethod,
    val grantType: String,
    val userInfoRequestHttpMethod: HttpMethod,
    val userInfoRequestUri: String,
    val userInfoResponseAttributes: Map<String, String>
) {

    val accessTokenRequest by lazy {
        AccessTokenRequest(
            httpMethod = accessTokenRequestHttpMethod,
            uri = accessTokenRequestUri,
            accessTokenRequestParameters = AccessTokenRequestParameters(
                grantType = grantType,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = "http://localhost:8080/auth/$clientName"
            )
        )
    }
    val userInfoRequest by lazy {
        UserInfoRequest(
            httpMethod = userInfoRequestHttpMethod,
            uri = userInfoRequestUri
        )
    }
    val accessTokenParameter: MultiValueMap<String, String> by lazy {
        LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("redirect_uri", "http://localhost:8080/auth/$clientName")
        }
    }

    class UserInfoAttributes(
        private val id: String,
        private val profileImage: String,
        private val email: String
    ) {

        val attributes by lazy {
            mapOf(
                "id" to id,
                "profileImage" to profileImage,
                "email" to email
            )
        }

    }

}
