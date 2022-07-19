package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.mock.web.MockHttpServletRequest

internal class DefaultResourceServerRequestFactoryTest {

    private val clients = hashMapOf(
        "google" to getResourceServer(
            clientName = "google",
            clientId = "googleClientId",
            clientSecret = "googleClientSecret",
            accessTokenRequestUri = "googleAccessTokenUri",
            userInfoRequestHttpMethod = HttpMethod.GET,
            userInfoRequestUri = "google_userInfoUri",
            userInfoResponseAttributes = mapOf(
                "id" to "googleId",
                "profileImage" to "googleProfile",
                "email" to "googleEmail"
            )
        ),
        "naver" to getResourceServer(
            clientName = "naver",
            clientId = "naverClientId",
            clientSecret = "naverClientSecret",
            accessTokenRequestUri = "naverAccessTokenUri",
            userInfoRequestHttpMethod = HttpMethod.GET,
            userInfoRequestUri = "naver_userInfoUri",
            userInfoResponseAttributes = mapOf(
                "id" to "naverId",
                "profileImage" to "naverProfile",
                "email" to "naverEmail"
            )
        ),
        "kakao" to getResourceServer(
            clientName = "kakao",
            clientId = "kakaoClientId",
            clientSecret = "kakaoClientSecret",
            accessTokenRequestUri = "kakaoAccessTokenUri",
            userInfoRequestHttpMethod = HttpMethod.GET,
            userInfoRequestUri = "kakao_userInfoUri",
            userInfoResponseAttributes = mapOf(
                "id" to "kakaoId",
                "profileImage" to "kakaoProfile",
                "email" to "kakaoEmail"
            )
        )
    )
    private val defaultOauth2AuthenticationCodeFactory = DefaultResourceServerRequestFactory(
        clients
    )

    @Test
    fun getOauth2AuthenticationCodeBy_return_oauth2AuthenticationCode_through_ServletRequest() {
        clients.forEach { client ->
            val resourceServer = client.value
            val servletRequest = MockHttpServletRequest("GET", "/auth/${resourceServer.clientName}")
            val authenticationCode = "${resourceServer.clientName}AuthenticationCode"
            servletRequest.addParameter("code", authenticationCode)
            val resourceServerRequest = defaultOauth2AuthenticationCodeFactory.getResourceServerRequestBy(servletRequest)
            val expectedResourceServerRequest = getResourceServerRequest(
                resourceServerName = resourceServer.clientName,
                accessTokenRequest = resourceServer.getAccessTokenRequest(authenticationCode),
                userInfoRequest = resourceServer.userInfoRequest,
                userInfoResponseAttributes = resourceServer.userInfoResponseAttributes
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
