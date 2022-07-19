package me.spring.web.inflearnadvancedlevel.proxy.app.v1

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.TraceStatus
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace

class OrderControllerV1Proxy(
    private val target: OrderControllerV1,
    private val logTrace: LogTrace
): OrderControllerV1 {

    override fun request(itemId: String): String {
        lateinit var status: TraceStatus
        return try {
            status = logTrace.begin("OrderController.request()")
            val result = target.request(itemId)
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }

}
