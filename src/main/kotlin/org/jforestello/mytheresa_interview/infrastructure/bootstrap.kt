package org.jforestello.mytheresa_interview.infrastructure

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository

fun getProductRepository(): ProductRepository = object: ProductRepository {
    override fun search(filters: List<ProductRepository.Filter>, limit: Int): List<Product> {
        return listOf(Product(
            "000003", "test", "boots", 12345
        ))
    }
}
