package me.spring.security.oauth2

import org.springframework.stereotype.Component

@Component
class DefaultOauth2UserProvider(
    private val resourceServerClient: ResourceServerClient
): Oauth2UserProvider {

    override fun getOauth2User(resourceServerRequest: ResourceServerRequest): Oauth2User {
        val oauth2AccessTokenResponse = resourceServerClient.getAccessToken(resourceServerRequest.getAccessTokenRequestEntity(), resourceServerRequest.getAccessTokenResponseType) ?: throw IllegalStateException()
        resourceServerClient.getUserInfo(resourceServerRequest.getUserInfoRequestEntity(oauth2AccessTokenResponse.accessToken), Map::class.java)

        return Oauth2User(
            id = "id",
            profileImage = "",
            email = "",
            resourceServerName = ""
        )
    }

}
