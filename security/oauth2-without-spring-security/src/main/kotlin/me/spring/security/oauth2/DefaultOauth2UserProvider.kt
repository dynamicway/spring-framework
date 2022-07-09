package me.spring.security.oauth2

import org.springframework.stereotype.Component

@Component
class DefaultOauth2UserProvider(
    private val resourceServerClient: ResourceServerClient
): Oauth2UserProvider {

    override fun getOauth2User(resourceServerRequest: ResourceServerRequest): Oauth2User {
        val oauth2AccessTokenResponse = resourceServerClient.getAccessToken(resourceServerRequest.accessTokenRequestEntity) ?: throw IllegalStateException("accessTokenResponse in null")
        val userInfoResponse = resourceServerClient.getUserInfo(resourceServerRequest.getUserInfoRequestEntity(oauth2AccessTokenResponse.accessToken)) ?: throw IllegalStateException("userInfoResponse is null")

        return Oauth2User(
            id = userInfoResponse.getAttribute(resourceServerRequest.userInfoResponseAttributes["id"]) ?: throw IllegalStateException("user info response has not id attribute that matched resource server id attribute"),
            profileImage = userInfoResponse.getAttribute(resourceServerRequest.userInfoResponseAttributes["profileImage"]),
            email = userInfoResponse.getAttribute(resourceServerRequest.userInfoResponseAttributes["email"]),
            resourceServerName = resourceServerRequest.resourceServerName
        )
    }

}
