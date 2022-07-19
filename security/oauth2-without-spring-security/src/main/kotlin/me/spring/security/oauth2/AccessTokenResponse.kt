package me.spring.security.oauth2

import com.fasterxml.jackson.annotation.JsonProperty

class AccessTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String
)
