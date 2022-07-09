package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResourceServerRequestTest {

    @Test
    fun accessTokenRequestEntity() {
        val accessTokenRequest = getAccessTokenRequest()
        val resourceServerRequest = getResourceServerRequest(
            accessTokenRequest = accessTokenRequest
        )
        assertThat(resourceServerRequest.accessTokenRequestEntity).isEqualTo(accessTokenRequest.accessTokenRequestEntity)
    }

    @Test
    fun userInfoRequestEntity() {
        val userInfoRequest = getUserInfoRequest()
        val resourceServerRequest = getResourceServerRequest(
            userInfoRequest = userInfoRequest
        )
        val accessToken = "accessToken"
        val userInfoRequestEntity = resourceServerRequest.getUserInfoRequestEntity(accessToken)
        assertThat(userInfoRequestEntity).isEqualTo(userInfoRequest.getAccessTokenRequestEntity(accessToken))
    }

}
