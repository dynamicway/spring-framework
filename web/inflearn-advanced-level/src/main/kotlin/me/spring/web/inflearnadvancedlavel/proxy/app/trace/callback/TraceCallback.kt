package me.spring.web.inflearnadvancedlavel.proxy.app.trace.callback

interface TraceCallback<T> {

    fun call(): T
}
