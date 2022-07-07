package me.spring.web.inflearnadvancedlevel.proxy.app.v3

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3 {

    fun save(itemId: String) {
        if (itemId == "ex")
            throw java.lang.IllegalStateException("예외 발생!")
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
