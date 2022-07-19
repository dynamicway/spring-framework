package me.spring.security.oauth2

data class Oauth2User(
    private val id: String,
    private val profileImage: String?,
    private val email: String?,
    private val resourceServerName: String
)
