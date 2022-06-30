package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap

internal class ResourceServerRequestTest {

    @Test
    fun getAccessTokenRequestEntity() {
        val userInfoHttpMethod = HttpMethod.GET
        val accessTokenHost = "https://www.googleapis.com"
        val accessTokenPath = "/oauth2/v3/userinfo"
        val accessTokenParameters = LinkedMultiValueMap<String, String>().apply {
            add("1", "1")
            add("2", "2")
            add("3", "3")
        }
        val resourceServerRequest = getResourceServerRequest(
            accessTokenUri = "$accessTokenHost$accessTokenPath",
            userInfoHttpMethod = userInfoHttpMethod,
            accessTokenParameters = accessTokenParameters
        )
        val accessTokenRequestEntity = resourceServerRequest.getAccessTokenRequestEntity()

        assertThat(accessTokenRequestEntity.method).isEqualTo(userInfoHttpMethod)
        assertThat("https://${accessTokenRequestEntity.url.host}${accessTokenRequestEntity.url.path}").isEqualTo("$accessTokenHost$accessTokenPath")
        assertThat(accessTokenRequestEntity.url.query).isEqualTo(accessTokenParameters.map { "${it.key}=${it.value[0]}" }
                .reduce { acc, s -> "$acc&$s" })
    }

    @Test
    fun getUserInfoRequestEntity() {
        val userInfoHttpMethod = HttpMethod.GET
        val userInfoHost = "https://www.googleapis.com"
        val userInfoPath = "/oauth2/v3/userinfo"
        val resourceServerRequest = getResourceServerRequest(
            userInfoUri = "$userInfoHost$userInfoPath",
            userInfoHttpMethod = userInfoHttpMethod
        )
        val accessToken = "accessToken"
        val userInfoRequestEntity = resourceServerRequest.getUserInfoRequestEntity(accessToken)

        assertThat(userInfoRequestEntity.method).isEqualTo(userInfoHttpMethod)
        assertThat("https://${userInfoRequestEntity.url.host}${userInfoRequestEntity.url.path}").isEqualTo("$userInfoHost$userInfoPath")
        assertThat(userInfoRequestEntity.headers[HttpHeaders.AUTHORIZATION]!![0]).isEqualTo("Bearer $accessToken")
    }

    @Test
    fun getUserAttribute_getUserInfoResponse_is_flat_map() {
        val userInfoAttributes = mapOf(
            "property1" to "1",
            "property2" to "2",
            "property3" to "3"
        )
        val resourceServerName = "google"
        val resourceServerRequest = getResourceServerRequest(
            resourceServerName = resourceServerName,
            userInfoAttributes = userInfoAttributes
        )
        val userAttributes = resourceServerRequest.getUserAttributes(
            mapOf(
                "1" to "one",
                "2" to "two",
                "3" to "three"
            )
        )

        assertThat(userAttributes).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                "property1" to "one",
                "property2" to "two",
                "property3" to "three",
                "resourceServerName" to resourceServerName
            )
        )
    }

    @Test
    fun getUserAttributes_getUserInfoResponse_is_not_flat_map() {
        val userInfoAttributes = mapOf(
            "property1" to "1",
            "property2" to "2.2",
            "property3" to "3.3.3"
        )
        val resourceServerName = "kakao"
        val resourceServerRequest = getResourceServerRequest(
            resourceServerName = resourceServerName,
            userInfoAttributes = userInfoAttributes
        )
        val userAttributes = resourceServerRequest.getUserAttributes(
            mapOf(
                "1" to "one",
                "2" to mapOf("2" to "two"),
                "3" to mapOf("3" to mapOf("3" to "three"))
            )
        )

        assertThat(userAttributes).containsExactlyInAnyOrderEntriesOf(
            mapOf(
                "property1" to "one",
                "property2" to "two",
                "property3" to "three",
                "resourceServerName" to resourceServerName
            )
        )
    }

    @Test
    fun getUserAttributes_throw_IllegalArgumentsException_when_getUserInfoResponse_has_no_attribute() {
        val userInfoAttributes = mapOf(
            "property1" to "1",
            "property2" to "2.2",
            "property3" to "3.3.3"
        )
        val resourceServerRequest = getResourceServerRequest(
            userInfoAttributes = userInfoAttributes
        )

        assertThatCode {
            resourceServerRequest.getUserAttributes(
                mapOf(
                    "1" to "one",
                    "2" to "2",
                    "3" to mapOf("3" to mapOf("3" to "three"))
                )
            )
        }.isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun getUserAttributes_throw_IllegalArgumentsException_when_getUserInfoResponse_attribute_is_null() {
        val userInfoAttributes = mapOf(
            "property1" to "1",
            "property2" to "2",
            "property3" to "3"
        )
        val resourceServerRequest = getResourceServerRequest(
            userInfoAttributes = userInfoAttributes
        )

        assertThatCode {
            resourceServerRequest.getUserAttributes(
                mapOf(
                    "1" to "one",
                    "2" to "two",
                )
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

}
