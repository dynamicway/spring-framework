package me.spring.web.oopandpp.transactionscript.application

import me.spring.web.oopandpp.transactionscript.domain.DiscountStrategy
import me.spring.web.oopandpp.transactionscript.domain.Order
import me.spring.web.oopandpp.transactionscript.domain.Rule
import me.spring.web.oopandpp.transactionscript.infrastructure.DiscountStrategyRepository
import me.spring.web.oopandpp.transactionscript.infrastructure.OrderRepository
import me.spring.web.oopandpp.transactionscript.infrastructure.ProductRepository
import me.spring.web.oopandpp.transactionscript.infrastructure.RuleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val ruleRepository: RuleRepository,
    private val discountStrategyRepository: DiscountStrategyRepository
) {

    @Transactional
    fun order(order: Order) {
        val product = productRepository.findByIdOrNull(order.productId)
            ?: throw IllegalArgumentException("no exists product productId: ${order.productId}")

        val discountStrategies = discountStrategyRepository.findAllByProductId(product.id)
        val rules = ruleRepository.findAllByDiscountStrategyIds(discountStrategies.map { it.id })

        val availableDiscountStrategies = mutableListOf<DiscountStrategy>()

        discountStrategies.forEach { discountStrategy ->
            val rule = rules.filter { it.discountStrategyId == discountStrategy.id }
                .firstOrNull { r ->
                    when (r.type) {
                        Rule.Type.SEQUENCE_CYCLE -> {
                            val sequenceCycle = r.sequenceCycle ?: throw NoSuchElementException()
                            order.productSequence % sequenceCycle == 0L
                        }
                        Rule.Type.PERIOD -> {
                            val sincePeriodTime = r.sincePeriodTime ?: throw NoSuchElementException()
                            val untilPeriodTime = r.untilPeriodTime ?: throw java.util.NoSuchElementException()
                            order.orderTime.isAfter(sincePeriodTime) && order.orderTime.isBefore(untilPeriodTime)
                        }
                    }
                }

            if (rule != null)
                availableDiscountStrategies.add(discountStrategy)
        }

        var maxDisCountAmount = 0

        availableDiscountStrategies.forEach { discountStrategy ->
            maxDisCountAmount = when (discountStrategy.strategy) {
                DiscountStrategy.Strategy.ABSOLUTE_VALUE -> maxOf(maxDisCountAmount, discountStrategy.value)
                DiscountStrategy.Strategy.PERCENTAGE_VALUE -> {
                    maxOf(maxDisCountAmount, product.fee * (1 - (discountStrategy.value / 100f)).toInt())
                }
            }
        }

        order.fee -= maxDisCountAmount
        orderRepository.save(order)

    }


}