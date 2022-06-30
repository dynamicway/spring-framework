package me.spring.security.oauth2

import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap

class Oauth2AuthenticationCode(
    val accessTokenUri: String,
    val accessTokenParameters: MultiValueMap<String, String>,
    val userInfoHttpMethod: HttpMethod,
    val userInfoUri: String,
    val userInfoAttributes: Map<String, String>,
    val resourceServerId: String,
    val code: String
)
