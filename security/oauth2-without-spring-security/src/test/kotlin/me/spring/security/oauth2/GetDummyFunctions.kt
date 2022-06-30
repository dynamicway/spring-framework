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
    userInfoAttributes: ResourceServer.UserInfoAttributes = ResourceServer.UserInfoAttributes(
        id = "id",
        profileImage = "profile",
        email = "email"
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
