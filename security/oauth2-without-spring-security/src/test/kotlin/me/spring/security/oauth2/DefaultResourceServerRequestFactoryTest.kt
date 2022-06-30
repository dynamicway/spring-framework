package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.util.LinkedMultiValueMap

internal class DefaultResourceServerRequestFactoryTest {

    private val clients = hashMapOf(
        "google" to getResourceServer(
            clientName = "google",
            clientId = "googleClientId",
            clientSecret = "googleClientSecret",
            accessTokenUri = "googleAccessTokenUri",
            accessTokenRequestParameters = mapOf("googleAccessTokenParameter" to "googleAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "google_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                id = "googleId",
                profileImage = "googleProfile",
                email = "googleEmail"
            )
        ),
        "naver" to getResourceServer(
            clientName = "naver",
            clientId = "naverClientId",
            clientSecret = "naverClientSecret",
            accessTokenUri = "naverAccessTokenUri",
            accessTokenRequestParameters = mapOf("naverAccessTokenParameter" to "naverAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "naver_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                id = "naverId",
                profileImage = "naverProfile",
                email = "naverEmail"
            )
        ),
        "kakao" to getResourceServer(
            clientName = "kakao",
            clientId = "kakaoClientId",
            clientSecret = "kakaoClientSecret",
            accessTokenUri = "kakaoAccessTokenUri",
            accessTokenRequestParameters = mapOf("kakaoAccessTokenParameter" to "kakaoAccessTokenParameter"),
            userInfoHttpMethod = HttpMethod.GET,
            userInfoUri = "kakao_userInfoUri",
            userInfoAttributes = ResourceServer.UserInfoAttributes(
                id = "kakaoId",
                profileImage = "kakaoProfile",
                email = "kakaoEmail"
            )
        )
    )
    private val defaultOauth2AuthenticationCodeFactory = DefaultResourceServerRequestFactory(
        clients
    )

    @Test
    fun getOauth2AuthenticationCodeBy_return_oauth2AuthenticationCode_through_authenticationCode_redirected() {
        clients.forEach { client ->
            val resourceServer = client.value
            val servletRequest = MockHttpServletRequest("GET", "/auth/${resourceServer.clientName}")
            val requestedAuthenticationCode = "${resourceServer.clientName}AuthenticationCode"
            servletRequest.addParameter("code", requestedAuthenticationCode)
            val resourceServerRequest = defaultOauth2AuthenticationCodeFactory.getResourceServerRequestBy(servletRequest)
            val accessTokenParameters = LinkedMultiValueMap<String, String>()
                    .apply {
                        putAll(resourceServer.accessTokenParameter)
                        add("code", requestedAuthenticationCode)
                    }
            val expectedResourceServerRequest = getResourceServerRequest(
                accessTokenUri = resourceServer.accessTokenUri,
                accessTokenParameters = accessTokenParameters,
                userInfoHttpMethod = resourceServer.userInfoHttpMethod,
                userInfoUri = resourceServer.userInfoUri,
                userInfoAttributes = resourceServer.userInfoAttributes.attributes
            )

            assertThat(resourceServerRequest).isEqualTo(expectedResourceServerRequest)
        }
    }

    @Test
    fun getOauth2AuthenticationCodyBy_throw_IllegalArgumentsException_when_not_supported_resource_server() {
        assertThatCode { defaultOauth2AuthenticationCodeFactory.getResourceServerRequestBy(MockHttpServletRequest("GET", "/auth/invalidResourceServer")) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("not supported resource server")
    }

}
