package me.spring.web.inflearnadvancedlevel.proxy.app.v2

import me.spring.web.inflearnadvancedlevel.util.NoArgsConstructor

@NoArgsConstructor
open class OrderRepositoryV2 {

    open fun save(itemId: String) {
        if (itemId == "ex")
            throw java.lang.IllegalStateException("예외 발생!")
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
