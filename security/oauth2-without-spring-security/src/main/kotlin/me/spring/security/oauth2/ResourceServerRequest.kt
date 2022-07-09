package me.spring.security.oauth2

data class ResourceServerRequest(
    val resourceServerName: String,
    private val accessTokenRequest: AccessTokenRequest,
    private val userInfoRequest: UserInfoRequest,
    val userInfoResponseAttributes: Map<String, String>
) {

    val accessTokenRequestEntity by lazy { accessTokenRequest.accessTokenRequestEntity }

    fun getUserInfoRequestEntity(accessToken: String) = userInfoRequest.getAccessTokenRequestEntity(accessToken)

}
