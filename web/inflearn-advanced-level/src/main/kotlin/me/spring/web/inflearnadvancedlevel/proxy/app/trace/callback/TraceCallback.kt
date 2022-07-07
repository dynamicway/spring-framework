package me.spring.web.inflearnadvancedlevel.proxy.app.trace.callback

interface TraceCallback<T> {

    fun call(): T
}
