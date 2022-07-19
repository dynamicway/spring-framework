package me.spring.web.oopandpp.domainmodel.application

import me.spring.web.oopandpp.domainmodel.domain.Order
import me.spring.web.oopandpp.domainmodel.infrastructure.DiscountStrategyRepository
import me.spring.web.oopandpp.domainmodel.infrastructure.OrderRepository
import me.spring.web.oopandpp.domainmodel.infrastructure.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val discountStrategyRepository: DiscountStrategyRepository,
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
) {

    @Transactional
    fun order(order: Order) {
        val product = productRepository.findByIdOrNull(order.productId) ?: throw IllegalArgumentException("no exists product productId: ${order.productId}")
        val discountStrategies = discountStrategyRepository.findAllByProductId(product.id)
        order.applyDiscountFee(discountStrategies)
        orderRepository.save(order)
    }

}
