package me.spring.security.oauth2

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*

class SpyRestTemplate: RestTemplate() {

    lateinit var getForEntityArgumentsUrl: URI
    lateinit var getForEntityArgumentsResponseType: Class<*>
    var getForEntityResult: ResponseEntity<Any> = ResponseEntity.of(Optional.of(Oauth2AccessTokenResponse("accessToken")))

    override fun <T: Any?> getForEntity(
        url: URI,
        responseType: Class<T>
    ): ResponseEntity<T> {
        getForEntityArgumentsUrl = url
        getForEntityArgumentsResponseType = responseType
        return getForEntityResult as ResponseEntity<T>
    }

}
