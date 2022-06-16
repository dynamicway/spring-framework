package me.spring.web.oopandpp.transactionscript

import java.time.LocalDateTime

class Rule(
    private val id: Long,
    private val discountStrategyId: Long,
    private val type: Type,
    private val sequenceCycle: Long?,
    private val sincePeriodTime: LocalDateTime?,
    private val untilPeriodTime: LocalDateTime?
) {

    enum class Type {
        SEQUENCE_CYCLE, PERIOD
    }
}