package me.spring.web.oopandpp.transactionscript.infrastructure

import me.spring.web.oopandpp.transactionscript.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}