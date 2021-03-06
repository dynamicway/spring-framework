package me.spring.security.oauth2

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = UserInfoResponseDeserializer::class)
class UserInfoResponse(
    private val userInfoAttributesResponse: Map<String, Any>
) {

    fun getAttribute(attribute: String?) = userInfoAttributesResponse[attribute]?.toString()

}
