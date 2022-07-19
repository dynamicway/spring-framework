package me.spring.web.oopandpp.domainmodel.domain

interface Rule {

    fun isSatisfied(order: Order): Boolean

}
