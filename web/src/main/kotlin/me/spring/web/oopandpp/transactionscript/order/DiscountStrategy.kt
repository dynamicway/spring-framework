package me.spring.web.oopandpp.transactionscript.order

class DiscountStrategy(
    private val id: Long,
    private val productId: Long,
    private val value: Int,
    private val strategy: Strategy
) {
    enum class Strategy {
        ABSOLUTE_VALUE,
        PERCENTAGE_VALUE
    }
}