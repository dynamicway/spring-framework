package me.spring.web.inflearnadvancedlavel.proxy.app.trace.logtrace

import me.spring.web.inflearnadvancedlavel.proxy.app.trace.TraceId
import me.spring.web.inflearnadvancedlavel.proxy.app.trace.TraceStatus
import org.slf4j.LoggerFactory

class FieldLogTrace: LogTrace {

    private val log = LoggerFactory.getLogger(FieldLogTrace::class.java)

    private var traceIdHolder //동시성 이슈 발생
            : TraceId? = null

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId: TraceId = traceIdHolder!!
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
        traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
    }

    private fun releaseTraceId() {
        traceIdHolder = if (traceIdHolder!!.isFirstLevel) {
            null //destroy
        } else {
            traceIdHolder!!.createPreviousId()
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
