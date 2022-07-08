package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

internal fun getResourceServer(
    clientName: String = "clientname",
    clientId: String = "clientid",
    clientSecret: String = "clientsecret",
    accessTokenUri: String = "https://accesTokenUri",
    accessTokenRequestParameters: Map<String, String> = mapOf("accesstokenparameter" to "accesstokenparameter"),
    userInfoHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoUri: String = "userinfouri",
    userInfoAttributes: Map<String, String> = mapOf(
        "id" to "id",
        "profileImage" to "profile",
        "email" to "email"
    )
) = ResourceServer(
    clientName = clientName,
    clientId = clientId,
    clientSecret = clientSecret,
    accessTokenUri = accessTokenUri,
    additionalAccessTokenRequestParameters = accessTokenRequestParameters,
    userInfoHttpMethod = userInfoHttpMethod,
    userInfoUri = userInfoUri,
    userInfoAttributes = userInfoAttributes
)

internal fun getResourceServerRequest(
    resourceServerName: String = "google",
    accessTokenUri: String = "https://accessTokenUri",
    accessTokenParameters: MultiValueMap<String, String> = LinkedMultiValueMap(),
    userInfoHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoUri: String = "userInfoUri",
    userInfoAttributes: Map<String, String> = mapOf(
        "id" to "id",
        "profileImage" to "profileImage",
        "email" to "email"
    ),
) = ResourceServerRequest(
    resourceServerName = resourceServerName,
    accessTokenUri = accessTokenUri,
    accessTokenParameters = accessTokenParameters,
    userInfoHttpMethod = userInfoHttpMethod,
    userInfoUri = userInfoUri,
    userInfoAttributes = userInfoAttributes,
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
    redirectUri: String = "redirectUri"
) = AccessTokenRequestParameters(
    grantType = grantType,
    clientId = clientId,
    clientSecret = clientSecret,
    redirectUri = redirectUri
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
