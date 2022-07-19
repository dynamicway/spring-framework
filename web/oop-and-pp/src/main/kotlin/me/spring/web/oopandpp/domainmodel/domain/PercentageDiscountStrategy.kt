package me.spring.web.oopandpp.domainmodel.domain

class PercentageDiscountStrategy(
    id: Long,
    product: Product,
    value: Int,
    rules: List<Rule>
): DiscountStrategy(id, product, value, rules) {

    override fun getDisCountFee(order: Order) = (order.fee * (value / 100f)).toInt()

}
