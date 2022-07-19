package me.spring.security.oauth2

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.wnameless.json.flattener.JsonFlattener

class UserInfoResponseDeserializer(vc: Class<*>?): StdDeserializer<UserInfoResponse>(vc) {

    constructor(): this(null)

    override fun deserialize(
        p: JsonParser,
        ctxt: DeserializationContext
    ): UserInfoResponse {
        val jsonNode = p.codec.readTree<JsonNode>(p)
        val json = jsonNode.toString()
        val flattenAttributes = JsonFlattener.flattenAsMap(json) as Map<String, Any>
        return UserInfoResponse(flattenAttributes)
    }

}
