package me.spring.web.inflearnadvancedlevel

import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.LogTrace
import me.spring.web.inflearnadvancedlevel.proxy.app.trace.logtrace.ThreadLocalLogTrace
import me.spring.web.inflearnadvancedlevel.proxy.config.AppV2Config
import me.spring.web.inflearnadvancedlevel.proxy.config.CgLibProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(CgLibProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["me.spring.web.inflearnadvancedlevel.proxy.app"])
class InflearnAdvancedLevelApplication {

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()

}

fun main(args: Array<String>) {
    runApplication<InflearnAdvancedLevelApplication>(*args)
}
