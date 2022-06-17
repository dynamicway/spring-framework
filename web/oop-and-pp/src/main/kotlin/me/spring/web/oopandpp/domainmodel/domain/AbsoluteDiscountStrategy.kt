package me.spring.web.oopandpp.domainmodel.domain

class AbsoluteDiscountStrategy(
    id: Long,
    product: Product,
    value: Int,
    rules: List<Rule>
): DiscountStrategy(id, product, value, rules) {

    override fun getDisCountFee(order: Order) = value

}
