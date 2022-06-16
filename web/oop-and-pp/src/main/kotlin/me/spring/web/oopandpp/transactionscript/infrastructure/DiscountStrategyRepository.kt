package me.spring.web.oopandpp.transactionscript.infrastructure

import me.spring.web.oopandpp.transactionscript.domain.DiscountStrategy
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountStrategyRepository: JpaRepository<DiscountStrategy, Long> {
    fun findAllByProductId(productId: Long): List<DiscountStrategy>
}