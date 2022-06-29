package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.mock.web.MockHttpServletRequest

internal class DefaultOauth2AuthenticationCodeFactoryTest {

    private val clients = hashMapOf(
        "google" to getResourceServerDummy(
            clientName = "googleClientName",
            clientId = "googleClientId",
            clientSecret = "googleClientSecret",
            resourceServerId = "googleResourceServerId",
            accessTokenUri = "googleAccessTokenUri",
            accessTokenRequestParameters = mapOf("googleAccessTokenParameter" to "googleAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "google_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profileImage = "googleProfile",
                email = "googleEmail"
            )
        ),
        "naver" to getResourceServerDummy(
            clientName = "naverClientName",
            clientId = "naverClientId",
            clientSecret = "naverClientSecret",
            resourceServerId = "naverResourceServerId",
            accessTokenUri = "naverAccessTokenUri",
            accessTokenRequestParameters = mapOf("naverAccessTokenParameter" to "naverAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "naver_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profileImage = "naverProfile",
                email = "naverEmail"
            )
        ),
        "kakao" to getResourceServerDummy(
            clientName = "kakaoClientName",
            clientId = "kakaoClientId",
            clientSecret = "kakaoClientSecret",
            resourceServerId = "kakaoResourceServerId",
            accessTokenUri = "kakaoAccessTokenUri",
            accessTokenRequestParameters = mapOf("kakaoAccessTokenParameter" to "kakaoAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "kakao_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                profileImage = "kakaoProfile",
                email = "kakaoEmail"
            )
        )
    )
    private val defaultOauth2AuthenticationCodeFactory = DefaultOauth2AuthenticationCodeFactory(
        clients
    )

    @Test
    fun getOauth2AuthenticationCodeBy_return_oauth2AuthenticationCode_through_authenticationCode_redirected() {
        clients.forEach { client ->
            val resourceServer = client.value
            val servletRequest = MockHttpServletRequest("GET", "/auth/${resourceServer.clientName}")
            val requestedAuthenticationCode = "${resourceServer.clientName}AuthenticationCode"
            servletRequest.addParameter("code", requestedAuthenticationCode)
            val authenticationCode = defaultOauth2AuthenticationCodeFactory.getOauth2AuthenticationCodeBy(servletRequest)

            assertThat(authenticationCode.accessTokenUri).isEqualTo(resourceServer.accessTokenUri)
            assertThat(authenticationCode.resourceServerId).isEqualTo(resourceServer.resourceServerId)
            assertThat(authenticationCode.accessTokenParameters).isEqualTo(resourceServer.accessTokenParameter)
            assertThat(authenticationCode.userInfoHttpMethod).isEqualTo(resourceServer.userInfoHttpMethod)
            assertThat(authenticationCode.userInfoUri).isEqualTo(resourceServer.userInfoUri)
            assertThat(authenticationCode.userInfoAttributes).isEqualTo(resourceServer.userInfoAttributes.attributes)
            assertThat(authenticationCode.code).isEqualTo(requestedAuthenticationCode)
        }
    }

    @Test
    fun getOauth2AuthenticationCodyBy_throw_IllegalArgumentsException_when_not_supported_resource_server() {
        assertThatCode { defaultOauth2AuthenticationCodeFactory.getOauth2AuthenticationCodeBy(MockHttpServletRequest("GET", "/auth/invalidResourceServer")) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("not supported resource server")
    }

}
