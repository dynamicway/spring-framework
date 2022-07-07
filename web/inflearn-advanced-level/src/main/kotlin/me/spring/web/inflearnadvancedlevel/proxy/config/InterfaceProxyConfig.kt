package me.spring.web.inflearnadvancedlevel.proxy.config

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderControllerV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderControllerV1Proxy
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderRepositoryV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderRepositoryV1Proxy
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderServiceV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderServiceV1Proxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {

    @Bean
    fun orderControllerV1(logTrace: LogTrace) = OrderControllerV1Proxy(
        OrderControllerV1Impl(orderServiceV1(logTrace)),
        logTrace
    )

    @Bean
    fun orderServiceV1(logTrace: LogTrace) = OrderServiceV1Proxy(
        OrderServiceV1Impl(orderRepositoryV1(logTrace)),
        logTrace
    )

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace) = OrderRepositoryV1Proxy(
        OrderRepositoryV1Impl(),
        logTrace
    )

}
