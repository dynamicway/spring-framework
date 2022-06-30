package me.spring.security.oauth2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

}
