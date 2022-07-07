package me.spring.web.inflearnadvancedlevel.proxy.app.v1

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.TraceStatus
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace

class OrderRepositoryV1Proxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
): OrderRepositoryV1 {

    override fun save(itemId: String) {
        lateinit var status: TraceStatus
        try {
            status = logTrace.begin("OrderRepository.save()")
            target.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}
