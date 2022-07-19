package me.spring.web.oopandpp.domainmodel.infrastructure

import me.spring.web.oopandpp.domainmodel.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>
