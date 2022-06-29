package me.spring.security.oauth2

import org.springframework.http.HttpMethod

class ResourceServer(
    val clientName: String,
    val clientId: String,
    val clientSecret: String,
    val resourceServerId: String,
    val accessTokenUri: String,
    private val additionalAccessTokenRequestParameters: Map<String, String>,
    val userInfoHttpMethod: HttpMethod,
    val userInfoUri: String,
    val userInfoAttributes: UserInfoAttributes
) {

    val accessTokenParameter: Map<String, String> by lazy {
        mutableMapOf(
            "grant_type" to "authentication_code",
            "client_id" to clientId,
            "client_secret" to clientSecret,
        ).apply { putAll(additionalAccessTokenRequestParameters) }
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
