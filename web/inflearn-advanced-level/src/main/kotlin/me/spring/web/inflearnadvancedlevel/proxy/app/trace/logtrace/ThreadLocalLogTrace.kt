package me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.TraceId
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.TraceStatus
import org.slf4j.LoggerFactory

class ThreadLocalLogTrace: LogTrace {

    private val log = LoggerFactory.getLogger(ThreadLocalLogTrace::class.java)

    private val traceIdHolder: ThreadLocal<TraceId> = ThreadLocal<TraceId>()
    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId: TraceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(
        status: TraceStatus,
        e: Exception?
    ) {
        complete(status, e)
    }

    private fun complete(
        status: TraceStatus,
        e: Exception?
    ) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.id, addSpace(COMPLETE_PREFIX, traceId.level), status.message, resultTimeMs)
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.id, addSpace(EX_PREFIX, traceId.level), status.message, resultTimeMs, e.toString())
        }
        releaseTraceId()
    }

    private fun syncTraceId() {
        val traceId: TraceId? = traceIdHolder.get()
        if (traceId == null) {
            traceIdHolder.set(TraceId())
        } else {
            traceIdHolder.set(traceId.createNextId())
        }
    }

    private fun releaseTraceId() {
        val traceId: TraceId = traceIdHolder.get()
        if (traceId.isFirstLevel) {
            traceIdHolder.remove() //destroy
        } else {
            traceIdHolder.set(traceId.createPreviousId())
        }
    }

    companion object {

        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
        private fun addSpace(
            prefix: String,
            level: Int
        ): String {
            val sb = StringBuilder()
            for (i in 0 until level) {
                sb.append(if (i == level - 1) "|$prefix" else "|   ")
            }
            return sb.toString()
        }
    }
}
