package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

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
    fun getOauth2User_get_accessToken_by_restTemplate() {
        val resourceServerRequest = getResourceServerRequest()
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyRestTemplate.exchangeArgumentsEntity).isEqualTo(resourceServerRequest.getAccessTokenRequestEntity())
        assertThat(spyRestTemplate.exchangeArgumentsResponseType).isEqualTo(resourceServerRequest.getAccessTokenResponseType)
    }

    @Test
    fun getOauth2User_throw_IllegalStateException_when_get_accessToken_returns_null() {
        spyRestTemplate.exchangeResult = ResponseEntity.noContent()
                .build()

        assertThatCode { defaultOauth2UserProvider.getOauth2User(getResourceServerRequest()) }.isInstanceOf(IllegalStateException::class.java)
    }

}
