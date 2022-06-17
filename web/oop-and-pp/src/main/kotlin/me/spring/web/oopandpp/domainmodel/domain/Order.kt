package me.spring.web.oopandpp.domainmodel.domain

import java.time.LocalDateTime

class Order(
    private val id: Long,
    private val product: Product,
    private val _sequenceOfProductOrdered: Long,
    private val _orderTime: LocalDateTime
) {

    private var _fee: Int = product.fee
    private var discountFee = 0

    val fee
        get() = _fee
    val sequenceOfProductOrdered
        get() = _sequenceOfProductOrdered
    val orderTime
        get() = _orderTime

    fun applyDiscountFee(discountStrategies: List<DiscountStrategy>) {
        discountFee = discountStrategies.filter { it.isSatisfied(this) }
            .maxOf { it.getDisCountFee(this) }
    }

}
