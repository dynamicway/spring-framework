package me.spring.security.oauth2

import org.springframework.http.HttpMethod

internal fun getResourceServer(
    clientName: String = "clientname",
    clientId: String = "clientid",
    clientSecret: String = "clientsecret",
    accessTokenRequestUri: String = "https://accesTokenUri",
    accessTokenRequestHttpMethod: HttpMethod = HttpMethod.POST,
    grantType: String = "grantType",
    userInfoRequestHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoRequestUri: String = "userinfouri",
    userInfoResponseAttributes: Map<String, String> = mapOf(
        "id" to "id",
        "profileImage" to "profile",
        "email" to "email"
    )
) = ResourceServer(
    clientName = clientName,
    clientId = clientId,
    clientSecret = clientSecret,
    accessTokenRequestUri = accessTokenRequestUri,
    accessTokenRequestHttpMethod = accessTokenRequestHttpMethod,
    grantType = grantType,
    userInfoRequestHttpMethod = userInfoRequestHttpMethod,
    userInfoRequestUri = userInfoRequestUri,
    userInfoResponseAttributes = userInfoResponseAttributes
)

internal fun getResourceServerRequest(
    resourceServerName: String = "google",
    accessTokenRequest: AccessTokenRequest = getAccessTokenRequest(),
    userInfoRequest: UserInfoRequest = getUserInfoRequest(),
    userInfoResponseAttributes: Map<String, String> = mapOf(
        "id" to "id",
        "profileImage" to "profileImage",
        "email" to "email"
    )
) = ResourceServerRequest(
    resourceServerName = resourceServerName,
    accessTokenRequest = accessTokenRequest,
    userInfoRequest = userInfoRequest,
    userInfoResponseAttributes = userInfoResponseAttributes
)

internal fun getUserInfoRequest(
    httpMethod: HttpMethod = HttpMethod.POST,
    uri: String = "https://userInfo"
) = UserInfoRequest(
    httpMethod = httpMethod,
    uri = uri
)

internal fun getAccessTokenRequestParameters(
    grantType: String = "grantType",
    clientId: String = "clientId",
    clientSecret: String = "clientSecret",
    redirectUri: String = "redirectUri",
    authenticationCode: String = "authenticationCode"
) = AccessTokenRequestParameters(
    grantType = grantType,
    clientId = clientId,
    clientSecret = clientSecret,
    redirectUri = redirectUri,
    authenticationCode = authenticationCode
)

internal fun getAccessTokenRequest(
    httpMethod: HttpMethod = HttpMethod.GET,
    uri: String = "https://accessTokenUri",
    accessTokenRequestParameters: AccessTokenRequestParameters = getAccessTokenRequestParameters()
) = AccessTokenRequest(
    httpMethod = httpMethod,
    uri = uri,
    accessTokenRequestParameters = accessTokenRequestParameters
)

internal fun getUserInfoResponse(
    userInfoResponse: Map<String, String> = mapOf(
        "id" to "id",
        "profileImage" to "profileImage",
        "email" to "email"
    )
) = UserInfoResponse(
    userInfoAttributesResponse = userInfoResponse
)

internal fun getAccessTokenResponse(
    accessToken: String = "accessToken"
) = AccessTokenResponse(
    accessToken = accessToken
)
