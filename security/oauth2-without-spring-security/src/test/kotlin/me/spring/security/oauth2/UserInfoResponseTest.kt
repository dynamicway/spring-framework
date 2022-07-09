package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserInfoResponseTest {

    @Test
    fun getAttribute() {
        val userInfoAttributesResponse = mapOf(
            "string" to "STRING",
            "integer" to 1,
            "double" to 3.3
        )
        val userInfoResponse = getUserInfoResponse(
            userInfoAttributesResponse
        )

        userInfoAttributesResponse.forEach {
            assertThat(userInfoResponse.getAttribute(it.key)).isEqualTo(it.value.toString())
        }
    }

    @Test
    fun getAttributes_return_null_when_has_no_attribute() {
        val userInfoResponse = getUserInfoResponse(
            userInfoResponse = mapOf()
        )

        assertThat(userInfoResponse.getAttribute("hasNoAttribute")).isNull()
    }

}
