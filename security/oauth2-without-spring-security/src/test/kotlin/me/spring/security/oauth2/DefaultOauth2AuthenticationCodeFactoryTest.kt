package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest

internal class DefaultOauth2AuthenticationCodeFactoryTest {

    private val clients = hashMapOf(
        "google" to ResourceServer(
            clientName = "google",
            clientId = "googleClientId",
            clientSecret = "googleClientSecret",
            userInfoUrl = "googleUserInfoUrl",
            resourceServerId = "googleResourceServerId",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profile = "googleProfile",
                email = "googleEmail"
            )
        ),
        "naver" to ResourceServer(
            clientName = "naver",
            clientId = "naverClientId",
            clientSecret = "naverClientSecret",
            userInfoUrl = "naverUserInfoUrl",
            resourceServerId = "naverResourceServerId",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profile = "naverProfile",
                email = "naverEmail"
            )
        ),
        "kakao" to ResourceServer(
            clientName = "kakao",
            clientId = "kakaoClientId",
            clientSecret = "kakaoClientSecret",
            userInfoUrl = "kakaoUserInfoUrl",
            resourceServerId = "kakaoResourceServerId",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profile = "kakaoProfile",
                email = "kakaoEmail"
            )
        )
    )

    private val defaultOauth2AuthenticationCodeFactory = DefaultOauth2AuthenticationCodeFactory(
        clients
    )

    @Test
    fun getOauth2AuthenticationCodeBy_return_oauth2AuthenticationCode_through_googleAuthenticationCodeRedirect() {
        val servletRequest = MockHttpServletRequest("GET", "/auth/google")
        val googleAuthenticationCode = "googleAuthenticationCode"
        servletRequest.addParameter("code", googleAuthenticationCode)
        val authenticationCode = defaultOauth2AuthenticationCodeFactory.getOauth2AuthenticationCodeBy(servletRequest)
        assertThat(authenticationCode.resourceServer).isEqualTo(clients["google"])
        assertThat(authenticationCode.code).isEqualTo(googleAuthenticationCode)
    }

}