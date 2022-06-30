package me.spring.security.oauth2

interface Oauth2UserProvider {

    fun getOauth2User(resourceServerRequest: ResourceServerRequest): Oauth2User

}
