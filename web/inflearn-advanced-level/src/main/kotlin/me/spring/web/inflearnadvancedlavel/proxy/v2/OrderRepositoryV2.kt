package me.spring.web.inflearnadvancedlavel.proxy.v2

class OrderRepositoryV2 {

    fun save(itemId: String) {
        if (itemId == "ex")
            throw java.lang.IllegalStateException("예외 발생!")
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
