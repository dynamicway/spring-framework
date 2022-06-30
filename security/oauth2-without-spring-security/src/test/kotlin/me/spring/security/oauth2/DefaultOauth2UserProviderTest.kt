package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import java.util.*

internal class DefaultOauth2UserProviderTest {

    private lateinit var defaultOauth2UserProvider: DefaultOauth2UserProvider
    private lateinit var spyRestTemplate: SpyRestTemplate

    @BeforeEach
    internal fun setUp() {
        spyRestTemplate = SpyRestTemplate()
        defaultOauth2UserProvider = DefaultOauth2UserProvider(
            restTemplate = spyRestTemplate
        )
    }

    @Test
    fun getOauth2User_get_accessToken_by_resourceServerRequest() {
        val resourceServerRequest = getResourceServerRequest()
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyRestTemplate.exchangeArgumentsEntity).contains(resourceServerRequest.getAccessTokenRequestEntity())
        assertThat(spyRestTemplate.exchangeArgumentsResponseType).contains(resourceServerRequest.getAccessTokenResponseType)
    }

    @Test
    fun getOauth2User_throw_IllegalStateException_when_get_accessToken_returns_null() {
        spyRestTemplate.exchangeResult = ResponseEntity.noContent()
                .build()

        assertThatCode { defaultOauth2UserProvider.getOauth2User(getResourceServerRequest()) }.isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun getOauth2User_get_userInfo_by_resourceServerRequest() {
        val resourceServerRequest = getResourceServerRequest()
        val accessToken = "accessToken"
        spyRestTemplate.exchangeResult = ResponseEntity.of(Optional.of(Oauth2AccessTokenResponse(accessToken = accessToken)))
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyRestTemplate.exchangeArgumentsEntity).contains(resourceServerRequest.getUserInfoRequestEntity(accessToken))
        assertThat(spyRestTemplate.exchangeArgumentsResponseType).contains(resourceServerRequest.getUserInfoRequestType)
    }

}
