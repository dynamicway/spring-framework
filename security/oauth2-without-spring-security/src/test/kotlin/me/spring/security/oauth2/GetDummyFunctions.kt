package me.spring.security.oauth2

import org.springframework.http.HttpMethod

fun getResourceServerDummy(
    clientName: String = "clientname",
    clientId: String = "clientid",
    clientSecret: String = "clientsecret",
    resourceServerId: String = "resourceserverid",
    accessTokenUri: String = "accesstokenuri",
    accessTokenRequestParameters: Map<String, String> = mapOf("accesstokenparameter" to "accesstokenparameter"),
    userInfoHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoUri: String = "userinfouri",
    userInfoAttributes: ResourceServer.UserInfoAttributes = ResourceServer.UserInfoAttributes(
        profileImage = "profile",
        email = "email"
    )
) = ResourceServer(
    clientName = clientName,
    clientId = clientId,
    clientSecret = clientSecret,
    resourceServerId = resourceServerId,
    accessTokenUri = accessTokenUri,
    additionalAccessTokenRequestParameters = accessTokenRequestParameters,
    userInfoHttpMethod = userInfoHttpMethod,
    userInfoUri = userInfoUri,
    userInfoAttributes = userInfoAttributes
)

fun getOauth2AuthenticationCodeDummy(
    accessTokenUri: String = "accessTokenUri",
    accessTokenParameters: Map<String, String> = mapOf("accessTokenParameter" to "accessTokenParameter"),
    userInfoHttpMethod: HttpMethod = HttpMethod.GET,
    userInfoUri: String = "userInfoUri",
    resourceServerId: String = "resourceServerId",
    userInfoAttributes: Map<String, String> = mapOf("userInfoAttribute" to "userInfoAttribute"),
    code: String = "code",
) = Oauth2AuthenticationCode(
    accessTokenUri = accessTokenUri,
    accessTokenParameters = accessTokenParameters,
    userInfoHttpMethod = userInfoHttpMethod,
    userInfoUri = userInfoUri,
    resourceServerId = resourceServerId,
    userInfoAttributes = userInfoAttributes,
    code = code
)
