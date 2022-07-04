package me.spring.web.inflearnadvancedlavel.proxy.v2

class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {

    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}
