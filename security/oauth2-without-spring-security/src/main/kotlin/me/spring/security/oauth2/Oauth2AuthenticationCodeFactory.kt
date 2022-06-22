package me.spring.security.oauth2

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "authentication.oauth2")
@ConstructorBinding
class Oauth2AuthenticationCodeFactory(clients: Map<String, ResourceServer>) {

    private val resourceServers: List<ResourceServer>

    init {
        resourceServers = clients.values.toList()
    }

}
