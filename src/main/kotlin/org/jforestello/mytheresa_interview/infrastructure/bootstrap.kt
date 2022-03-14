package org.jforestello.mytheresa_interview.infrastructure

import org.jforestello.mytheresa_interview.domain.contract.ProductSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher
import org.jforestello.mytheresa_interview.infrastructure.product.ProductStorage

private val storage = ProductStorage()

fun getProductSearcher(): ProductSearcher = storage

fun getProductSaver(): ProductSaver = storage
