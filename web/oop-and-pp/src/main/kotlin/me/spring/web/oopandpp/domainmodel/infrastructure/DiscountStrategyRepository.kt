package me.spring.web.oopandpp.domainmodel.infrastructure

import me.spring.web.oopandpp.domainmodel.domain.DiscountStrategy
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountStrategyRepository: JpaRepository<DiscountStrategy, Long> {

    fun findAllByProductId(id: Long): List<DiscountStrategy>

}
