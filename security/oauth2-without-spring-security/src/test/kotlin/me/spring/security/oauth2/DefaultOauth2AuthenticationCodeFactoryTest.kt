package me.spring.security.oauth2

internal class DefaultOauth2AuthenticationCodeFactoryTest {

    private val defaultOauth2AuthenticationCodeFactory = DefaultOauth2AuthenticationCodeFactory(
        hashMapOf(
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
    )


}
