package me.spring.web.oopandpp.transactionscript.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Order(
    @Id
    val id: Long,
    val productId: Long,
    var fee: Int,
    val productSequence: Long,
    val orderTime: LocalDateTime
)