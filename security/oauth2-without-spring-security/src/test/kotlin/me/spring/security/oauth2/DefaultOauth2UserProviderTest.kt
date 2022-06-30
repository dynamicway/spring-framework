package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DefaultOauth2UserProviderTest {

    private lateinit var defaultOauth2UserProvider: DefaultOauth2UserProvider
    private lateinit var spyResourceServerClient: SpyResourceServerClient

    @BeforeEach
    internal fun setUp() {
        spyResourceServerClient = SpyResourceServerClient()
        defaultOauth2UserProvider = DefaultOauth2UserProvider(
            resourceServerClient = spyResourceServerClient
        )
    }

    @Test
    fun getOauth2User_get_accessToken_by_resourceServerRequest() {
        val resourceServerRequest = getResourceServerRequest()
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyResourceServerClient.getAccessTokenArgumentsEntity).isEqualTo(resourceServerRequest.getAccessTokenRequestEntity())
        assertThat(spyResourceServerClient.getAccessTokenArgumentsResponseType).isEqualTo(resourceServerRequest.getAccessTokenResponseType)
    }

    @Test
    fun getOauth2User_throw_IllegalStateException_when_get_accessToken_returns_null() {
        spyResourceServerClient.getAccessTokenResult = null

        assertThatCode { defaultOauth2UserProvider.getOauth2User(getResourceServerRequest()) }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun getOauth2User_get_userInfo_by_resourceServerRequest() {
        val resourceServerRequest = getResourceServerRequest()
        val accessToken = "accessToken"
        spyResourceServerClient.getAccessTokenResult = Oauth2AccessTokenResponse(accessToken = accessToken)
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyResourceServerClient.getUserInfoArgumentsEntity).isEqualTo(resourceServerRequest.getUserInfoRequestEntity(accessToken))
        assertThat(spyResourceServerClient.getUserInfoArgumentsResponseType).isEqualTo(resourceServerRequest.getUserInfoResponseType)
    }

}
