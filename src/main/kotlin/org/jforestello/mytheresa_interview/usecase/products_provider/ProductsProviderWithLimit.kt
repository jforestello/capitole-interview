package org.jforestello.mytheresa_interview.usecase.products_provider

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository

class ProductsProviderWithLimit(
    private val limit: Int,
    private val repository: ProductRepository
) {

    operator fun invoke(category: String?, maxPrice: Int?): List<Product> {
        val filters = listOfNotNull(
            category?.let {
                ProductRepository.Filter.Category(it)
            },
            maxPrice?.let {
                ProductRepository.Filter.MaxPrice(it)
            }
        )

        return repository.search(filters, limit)
    }
}