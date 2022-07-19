package me.spring.web.inflearnadvancedlevel.proxy.config

import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderControllerV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderRepositoryV1Impl
import me.spring.web.inflearnadvancedlevel.proxy.app.v1.OrderServiceV1Impl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppV1Config {

    @Bean
    fun orderControllerV1() = OrderControllerV1Impl(orderServiceV1())

    @Bean
    fun orderServiceV1() = OrderServiceV1Impl(orderRepositoryV1())

    @Bean
    fun orderRepositoryV1() = OrderRepositoryV1Impl()

}
