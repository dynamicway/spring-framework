package me.spring.web.inflearnadvancedlevel.proxy.app.v2

import me.spring.web.inflearnadvancedlevel.util.NoArgsConstructor

@NoArgsConstructor
open class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {

    open fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}
