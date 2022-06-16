package me.spring.web.oopandpp.transactionscript.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Rule(
    @Id
    val id: Long,
    val discountStrategyId: Long,
    val type: Type,
    val sequenceCycle: Long?,
    val sincePeriodTime: LocalDateTime?,
    val untilPeriodTime: LocalDateTime?
) {

    enum class Type {
        SEQUENCE_CYCLE, PERIOD
    }
}