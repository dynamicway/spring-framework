package me.spring.web.inflearnadvancedlevel.proxy.app.trace

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class LogTraceConcreteClassHandler(
    private val target: Any,
    private val logTrace: LogTrace
): MethodInterceptor {


    override fun intercept(
        p0: Any,
        p1: Method,
        p2: Array<out Any>,
        p3: MethodProxy
    ): Any? {
        val message = "${p1.declaringClass.simpleName}${p1.name}()"
        val status = logTrace.begin(message)
        val result = p3.invoke(target, p2)
        logTrace.end(status)
        return result
    }
}
