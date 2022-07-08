package me.spring.security.oauth2

import org.springframework.util.LinkedMultiValueMap

data class AccessTokenRequestParameters(
    private val grantType: String,
    private val clientId: String,
    private val clientSecret: String,
    private val redirectUri: String
) {

    val parameters by lazy {
        LinkedMultiValueMap<String, String>().apply {
            add("grant_type", grantType)
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("redirect_uri", redirectUri)
        }
    }

}
