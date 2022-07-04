package me.spring.web.inflearnadvancedlavel

import me.spring.web.inflearnadvancedlavel.proxy.app.trace.logtrace.LogTrace
import me.spring.web.inflearnadvancedlavel.proxy.app.trace.logtrace.ThreadLocalLogTrace
import me.spring.web.inflearnadvancedlavel.proxy.config.AppV1Config
import me.spring.web.inflearnadvancedlavel.proxy.config.AppV2Config
import me.spring.web.inflearnadvancedlavel.proxy.config.InterfaceProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(InterfaceProxyConfig::class, AppV2Config::class)
@SpringBootApplication(scanBasePackages = ["me.spring.web.inflearnadvancedlavel.proxy.app"])
class InflearnAdvancedLevelApplication {

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()

}

fun main(args: Array<String>) {
    runApplication<InflearnAdvancedLevelApplication>(*args)
}
