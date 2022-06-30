package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

fun getResourceServerDummy(
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

fun getResourceServerRequestDummy(
    accessTokenUri: String = "https://accessTokenUri",
    accessTokenParameters: MultiValueMap<String, String> = LinkedMultiValueMap(),
    userInfoHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoUri: String = "userInfoUri",
    userInfoAttributes: Map<String, String> = mapOf("userInfoAttribute" to "userInfoAttribute"),
) = ResourceServerRequest(
    accessTokenUri = accessTokenUri,
    accessTokenParameters = accessTokenParameters,
    userInfoHttpMethod = userInfoHttpMethod,
    userInfoUri = userInfoUri,
    userInfoAttributes = userInfoAttributes,
)
