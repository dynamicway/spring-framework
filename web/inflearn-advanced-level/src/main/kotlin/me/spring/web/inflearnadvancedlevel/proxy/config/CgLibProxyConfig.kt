package me.spring.web.inflearnadvancedlevel.proxy.config

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.LogTraceConcreteClassHandler
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import me.spring.web.inflearnadvancedlevel.proxy.app.v2.OrderControllerV2
import me.spring.web.inflearnadvancedlevel.proxy.app.v2.OrderRepositoryV2
import me.spring.web.inflearnadvancedlevel.proxy.app.v2.OrderServiceV2
import org.springframework.cglib.proxy.Enhancer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CgLibProxyConfig {

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV2 {
        val orderController = OrderControllerV2(orderService(logTrace))
        val enhancer = Enhancer()
        enhancer.setSuperclass(OrderControllerV2::class.java)
        enhancer.setCallback(LogTraceConcreteClassHandler(orderController, logTrace))
        return enhancer.create() as OrderControllerV2
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV2 {
        val orderService = OrderServiceV2(orderRepository(logTrace))
        val enhancer = Enhancer()
        enhancer.setSuperclass(OrderServiceV2::class.java)
        enhancer.setCallback(LogTraceConcreteClassHandler(orderService, logTrace))
        return enhancer.create() as OrderServiceV2
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepository = OrderRepositoryV2()
        val enhancer = Enhancer()
        enhancer.setSuperclass(OrderRepositoryV2::class.java)
        enhancer.setCallback(LogTraceConcreteClassHandler(orderRepository, logTrace))
        return enhancer.create() as OrderRepositoryV2
    }

}
