package me.spring.security.oauth2

class ResourceServer(
    val clientName: String,
    val clientId: String,
    val clientSecret: String,
    val userInfoUrl: String,
    val resourceServerId: String,
    val userInfoAttributes: UserInfoAttributes,
) {

    class UserInfoAttributes(
        val profile: String,
        val email: String
    )

}
