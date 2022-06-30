package me.spring.security.oauth2

import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.util.*

class SpyRestTemplate: RestTemplate() {

    lateinit var exchangeArgumentsEntity: RequestEntity<*>
    lateinit var exchangeArgumentsResponseType: Class<*>
    var exchangeResult: ResponseEntity<Any> = ResponseEntity.of(Optional.of(Oauth2AccessTokenResponse("accessToken")))

    override fun <T: Any?> exchange(
        entity: RequestEntity<*>,
        responseType: Class<T>
    ): ResponseEntity<T> {
        exchangeArgumentsEntity = entity
        exchangeArgumentsResponseType = responseType
        return exchangeResult as ResponseEntity<T>
    }

}
