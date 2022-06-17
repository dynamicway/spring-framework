package me.spring.web.oopandpp.domainmodel.domain

import java.time.LocalDateTime

class PeriodRule(
    private val sincePeriodTime: LocalDateTime,
    private val untilPeriodTime: LocalDateTime
): Rule {

    override fun isSatisfied(order: Order) = order.orderTime.isAfter(sincePeriodTime) && order.orderTime.isBefore(untilPeriodTime)

}
