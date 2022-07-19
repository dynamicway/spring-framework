package me.spring.security.oauth2

import org.springframework.http.HttpMethod

class ResourceServer(
    val clientName: String,
    private val clientId: String,
    private val clientSecret: String,
    private val accessTokenRequestUri: String,
    private val accessTokenRequestHttpMethod: HttpMethod,
    private val grantType: String,
    private val userInfoRequestHttpMethod: HttpMethod,
    private val userInfoRequestUri: String,
    val userInfoResponseAttributes: Map<String, String>
) {

    fun getAccessTokenRequest(authenticationCode: String) =
        AccessTokenRequest(
            httpMethod = accessTokenRequestHttpMethod,
            uri = accessTokenRequestUri,
            accessTokenRequestParameters = AccessTokenRequestParameters(
                grantType = grantType,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = "http://localhost:8080/auth/$clientName",
                authenticationCode
            )
        )

    val userInfoRequest by lazy {
        UserInfoRequest(
            httpMethod = userInfoRequestHttpMethod,
            uri = userInfoRequestUri
        )
    }

}
