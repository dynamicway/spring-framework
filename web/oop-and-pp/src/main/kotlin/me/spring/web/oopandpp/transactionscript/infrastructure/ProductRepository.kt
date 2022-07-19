package me.spring.web.oopandpp.transactionscript.infrastructure

import me.spring.web.oopandpp.transactionscript.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

}