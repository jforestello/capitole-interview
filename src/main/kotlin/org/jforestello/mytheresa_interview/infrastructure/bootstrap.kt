package org.jforestello.mytheresa_interview.infrastructure

import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.jforestello.mytheresa_interview.infrastructure.product.ProductStorage

fun getProductRepository(): ProductRepository = ProductStorage()
