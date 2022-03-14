package org.jforestello.mytheresa_interview.domain.contract

import org.jforestello.mytheresa_interview.domain.Product

interface ProductSaver {

    suspend fun save(product: Product)
}
