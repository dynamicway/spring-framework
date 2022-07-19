package me.spring.web.oopandpp.domainmodel.infrastructure

import me.spring.web.oopandpp.domainmodel.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long>
