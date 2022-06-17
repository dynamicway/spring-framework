package me.spring.web.oopandpp.domainmodel.domain

import java.time.LocalDateTime

class Order(
    private val id: Long,
    private val product: Product,
    private var fee: Int,
    private val sequenceOfProductOrdered: Long,
    private val orderTime: LocalDateTime
) {

    fun discount(discountStrategies: List<DiscountStrategy>) {
        fee -= discountStrategies.filter { it.isSatisfied(this) }
            .maxOf { it.getDisCountFee(this) }
    }

}
