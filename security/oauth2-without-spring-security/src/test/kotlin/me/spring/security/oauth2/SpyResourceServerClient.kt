package me.spring.security.oauth2

import org.springframework.http.RequestEntity

class SpyResourceServerClient: ResourceServerClient {

    lateinit var getAccessTokenArgumentsEntity: RequestEntity<Void>
    var getAccessTokenResult: AccessTokenResponse? = getAccessTokenResponse()
    lateinit var getUserInfoArgumentsEntity: RequestEntity<Void>
    var getUserInfoResult: UserInfoResponse? = getUserInfoResponse()

    override fun getAccessToken(accessTokenRequest: RequestEntity<Void>): AccessTokenResponse? {
        getAccessTokenArgumentsEntity = accessTokenRequest
        return getAccessTokenResult
    }

    override fun getUserInfo(userInfoRequest: RequestEntity<Void>): UserInfoResponse? {
        getUserInfoArgumentsEntity = userInfoRequest
        return getUserInfoResult
    }

}
