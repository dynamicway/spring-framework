package me.spring.web.inflearnadvancedlavel.proxy.app.trace.logtrace

import me.spring.web.inflearnadvancedlavel.proxy.app.trace.TraceStatus

interface LogTrace {

    fun begin(message: String): TraceStatus
    fun end(status: TraceStatus)
    fun exception(
        status: TraceStatus,
        e: Exception?
    )
}
