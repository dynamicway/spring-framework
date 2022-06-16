package me.spring.web.oopandpp.transactionscript.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DiscountStrategy(
    @Id
    val id: Long,
    val productId: Long,
    val value: Int,
    val strategy: Strategy
) {
    enum class Strategy {
        ABSOLUTE_VALUE,
        PERCENTAGE_VALUE
    }
}
