package me.spring.security.oauth2

class Oauth2AuthenticationCode(
    private val resourceServer: ResourceServer,
    val authenticationCode: String
)
