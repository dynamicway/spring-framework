package me.spring.security.oauth2

interface Oauth2UserProvider {

    fun getOauth2User(authenticationCode: Oauth2AuthenticationCode): Oauth2User

}
