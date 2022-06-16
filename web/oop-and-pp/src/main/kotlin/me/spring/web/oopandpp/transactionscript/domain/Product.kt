package me.spring.web.oopandpp.transactionscript.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    val id: Long,
    val fee: Int
)
