package me.spring.web.inflearnadvancedlavel.proxy.v1

class OrderServiceV1Impl(
    private val orderRepository: OrderRepositoryV1
): OrderServiceV1 {

    override fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}
