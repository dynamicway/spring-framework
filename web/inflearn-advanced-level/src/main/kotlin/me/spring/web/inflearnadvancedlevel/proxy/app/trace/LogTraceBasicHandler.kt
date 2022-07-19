package me.spring.web.inflearnadvancedlevel.proxy.app.trace

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace
): InvocationHandler {

    override fun invoke(
        proxy: Any,
        method: Method,
        args: Array<out Any>?
    ): Any? {

        lateinit var status: TraceStatus
        return try {
            val message = "${method.declaringClass.simpleName}.${method.name}()"
            status = logTrace.begin(message)
            val result = method.invoke(target, *args!!)
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }

    }
}
