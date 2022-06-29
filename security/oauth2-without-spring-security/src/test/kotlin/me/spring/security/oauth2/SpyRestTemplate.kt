package me.spring.security.oauth2

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.net.URI

class SpyRestTemplate: RestTemplate() {

    lateinit var getForEntityArgumentsUrl: URI
    lateinit var getForEntityArgumentsResponseType: Class<*>

    override fun <T: Any?> getForEntity(
        url: URI,
        responseType: Class<T>
    ): ResponseEntity<T> {
        getForEntityArgumentsUrl = url
        getForEntityArgumentsResponseType = responseType
        return ResponseEntity.noContent()
                .build()
    }

}
