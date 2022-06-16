package me.spring.web.oopandpp.transactionscript.infrastructure

import me.spring.web.oopandpp.transactionscript.domain.Rule
import org.springframework.data.jpa.repository.JpaRepository

interface RuleRepository : JpaRepository<Rule, Long> {
    fun findAllByDiscountStrategyIds(disCountStrategiesIds: List<Long>): List<Rule>
}