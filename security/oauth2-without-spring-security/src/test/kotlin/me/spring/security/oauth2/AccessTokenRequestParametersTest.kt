package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.util.LinkedMultiValueMap

internal class AccessTokenRequestParametersTest {

    @Test
    fun getParameters() {
        val grantType = "grantType"
        val clientId = "clientId"
        val clientSecret = "clientSecret"
        val redirectUri = "redirectUri"
        val accessTokenRequestParameters = getAccessTokenRequestParameters(
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret,
            redirectUri = redirectUri
        )
        val parameters = accessTokenRequestParameters.parameters
        val expectedParameters = LinkedMultiValueMap<String, String>()
                .apply {
                    add("grant_type", grantType)
                    add("client_id", clientId)
                    add("client_secret", clientSecret)
                    add("redirect_uri", redirectUri)
                }
        assertThat(parameters).isEqualTo(expectedParameters)
    }

}
