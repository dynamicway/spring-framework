package me.spring.web.inflearnadvancedlevel.proxy.app.trace.template

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.TraceStatus
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {

    fun execute(message: String): T {
        var status: TraceStatus? = null
        return try {
            status = trace.begin(message)
            //로직 호출
            val result = call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

    protected abstract fun call(): T
}
