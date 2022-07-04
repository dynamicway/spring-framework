package me.spring.web.inflearnadvancedlavel.proxy.v1

class OrderControllerV1Impl(
    private val orderService: OrderServiceV1
): OrderControllerV1 {

    override fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }

    override fun request(): String {
        return "ok"
    }
}
