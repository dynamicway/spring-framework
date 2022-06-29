package me.spring.security.oauth2

import com.fasterxml.jackson.annotation.JsonProperty

class Oauth2AccessTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String
)
