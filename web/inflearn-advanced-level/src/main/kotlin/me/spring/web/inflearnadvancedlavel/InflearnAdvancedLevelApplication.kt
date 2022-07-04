package me.spring.web.inflearnadvancedlavel

import me.spring.web.inflearnadvancedlavel.proxy.config.AppV1Config
import me.spring.web.inflearnadvancedlavel.proxy.config.AppV2Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class, AppV2Config::class)
@SpringBootApplication(scanBasePackages = ["me.spring.web.inflearnadvancedlavel.proxy.v1"])
class InflearnAdvancedLevelApplication

fun main(args: Array<String>) {
    runApplication<InflearnAdvancedLevelApplication>(*args)
}
