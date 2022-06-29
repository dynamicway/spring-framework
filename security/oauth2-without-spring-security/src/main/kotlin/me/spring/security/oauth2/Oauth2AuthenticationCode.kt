package me.spring.security.oauth2

import org.springframework.http.HttpMethod

class Oauth2AuthenticationCode(
    val accessTokenUri: String,
    val accessTokenParameters: Map<String, String>,
    val userInfoHttpMethod: HttpMethod,
    val userInfoUri: String,
    val userInfoAttributes: Map<String, String>,
    val resourceServerId: String,
    val code: String
)
