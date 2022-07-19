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

        assertThat(spyResourceServerClient.getAccessTokenArgumentsEntity).isEqualTo(resourceServerRequest.accessTokenRequestEntity)
    }

    @Test
    fun getOauth2User_throw_IllegalStateException_when_get_accessToken_returns_null() {
        spyResourceServerClient.getAccessTokenResult = null

        assertThatCode { defaultOauth2UserProvider.getOauth2User(getResourceServerRequest()) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("accessTokenResponse in null")
    }

    @Test
    fun getOauth2User_get_userInfo_by_resourceServerRequest() {
        val resourceServerRequest = getResourceServerRequest()
        val accessToken = "accessToken"
        spyResourceServerClient.getAccessTokenResult = AccessTokenResponse(accessToken = accessToken)
        defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(spyResourceServerClient.getUserInfoArgumentsEntity).isEqualTo(resourceServerRequest.getUserInfoRequestEntity(accessToken))
    }

    @Test
    fun getOauth2User_throw_IllegalStateException_when_get_userInfo_is_null() {
        spyResourceServerClient.getUserInfoResult = null
        assertThatCode { defaultOauth2UserProvider.getOauth2User(getResourceServerRequest()) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("userInfoResponse is null")
    }

    @Test
    fun getOauth2User_return_Oauth2User_by_resourceServerRequest_with_userInfo() {
        val userInfo = mapOf(
            "id" to "resourceServerId",
            "profileImage" to "resourceServerProfileImage",
            "email" to "resourceServerEmail"
        )
        spyResourceServerClient.getUserInfoResult = getUserInfoResponse(
            userInfo
        )
        val resourceServerRequest = getResourceServerRequest(
            userInfoResponseAttributes = mapOf(
                "id" to "id",
                "profileImage" to "profileImage",
                "email" to "email"
            )
        )
        val expectedOauth2User = Oauth2User(
            id = userInfo[resourceServerRequest.userInfoResponseAttributes["id"]]!!,
            profileImage = userInfo[resourceServerRequest.userInfoResponseAttributes["profileImage"]],
            email = userInfo[resourceServerRequest.userInfoResponseAttributes["email"]],
            resourceServerName = resourceServerRequest.resourceServerName
        )
        val oauth2User = defaultOauth2UserProvider.getOauth2User(resourceServerRequest)

        assertThat(oauth2User).isEqualTo(expectedOauth2User)
    }

    @Test
    fun getOauth2User_throws_IllegalStateException_when_userInfoResponse_id_is_null() {
        val userInfo = mapOf(
            "profileImage" to "resourceServerProfileImage",
            "email" to "resourceServerEmail"
        )
        spyResourceServerClient.getUserInfoResult = getUserInfoResponse(
            userInfo
        )
        val resourceServerRequest = getResourceServerRequest(
            userInfoResponseAttributes = mapOf(
                "id" to "id",
                "profileImage" to "profileImage",
                "email" to "email"
            )
        )

        assertThatCode { defaultOauth2UserProvider.getOauth2User(resourceServerRequest) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("user info response has not id attribute that matched resource server id attribute")

    }

}
