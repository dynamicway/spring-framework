package me.spring.web.oopandpp.domainmodel.domain

abstract class DiscountStrategy(
    private val id: Long,
    private val product: Product,
    private val value: Int,
    private val rules: List<Rule>
) {

    fun isSatisfied(order: Order) = rules.any { it.isSatisfied(order) }

    abstract fun getDisCountFee(order: Order): Int

}
