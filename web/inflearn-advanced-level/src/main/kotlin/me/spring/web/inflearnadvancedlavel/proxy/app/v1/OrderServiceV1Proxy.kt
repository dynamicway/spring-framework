package me.spring.web.inflearnadvancedlavel.proxy.app.v1

import me.spring.web.inflearnadvancedlavel.proxy.app.trace.TraceStatus
import me.spring.web.inflearnadvancedlavel.proxy.app.trace.logtrace.LogTrace

class OrderServiceV1Proxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
): OrderServiceV1 {

    override fun orderItem(itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = logTrace.begin("OrderServiceV1Proxy.orderItem()")
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}
