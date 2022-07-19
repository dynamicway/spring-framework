package me.spring.web.oopandpp.domainmodel.domain

class SequenceCycleRule(
    private val sequenceCycle: Int
): Rule {

    override fun isSatisfied(order: Order) = order.sequenceOfProductOrdered % sequenceCycle == 0L

}
