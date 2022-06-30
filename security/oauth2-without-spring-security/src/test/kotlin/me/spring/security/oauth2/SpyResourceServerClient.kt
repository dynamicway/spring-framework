package me.spring.security.oauth2

import org.springframework.http.RequestEntity

class SpyResourceServerClient: ResourceServerClient {

    lateinit var getAccessTokenArgumentsResponseType: Class<*>
    lateinit var getAccessTokenArgumentsEntity: RequestEntity<Void>
    var getAccessTokenResult: Any? = Oauth2AccessTokenResponse("accessToken")

    lateinit var getUserInfoArgumentsResponseType: Class<*>
    lateinit var getUserInfoArgumentsEntity: RequestEntity<Void>
    var getUserInfoResult: Any? = mapOf<String, String>()

    override fun <T> getAccessToken(
        accessTokenRequest: RequestEntity<Void>,
        accessTokenResponseType: Class<T>
    ): T? {
        getAccessTokenArgumentsEntity = accessTokenRequest
        getAccessTokenArgumentsResponseType = accessTokenResponseType
        return getAccessTokenResult as T
    }

    override fun <T> getUserInfo(
        userInfoRequest: RequestEntity<Void>,
        userInfoResponseType: Class<T>
    ): T? {
        getUserInfoArgumentsEntity = userInfoRequest
        getUserInfoArgumentsResponseType = userInfoResponseType
        return getUserInfoResult as T
    }

}
