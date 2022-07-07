package me.spring.web.inflearnadvancedlevel.proxy.config

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.LogTraceBasicHandler
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderControllerV1
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderControllerV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderRepositoryV1
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderRepositoryV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderServiceV1
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderServiceV1Impl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyConfig {

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val orderController = OrderControllerV1Impl(orderService(logTrace))
        return Proxy.newProxyInstance(OrderControllerV1::class.java.classLoader, arrayOf(OrderControllerV1::class.java), LogTraceBasicHandler(orderController, logTrace)) as OrderControllerV1
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val orderService = OrderServiceV1Impl(orderRepository(logTrace))
        return Proxy.newProxyInstance(OrderServiceV1::class.java.classLoader, arrayOf(OrderServiceV1::class.java), LogTraceBasicHandler(orderService, logTrace)) as OrderServiceV1
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepository = OrderRepositoryV1Impl()
        return Proxy.newProxyInstance(OrderRepositoryV1::class.java.classLoader, arrayOf(OrderRepositoryV1::class.java), LogTraceBasicHandler(orderRepository, logTrace)) as OrderRepositoryV1
    }

}
