package org.jforestello.mytheresa_interview.usecase.products_provider

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher

class ProductsProviderWithLimit(
    private val limit: Int,
    private val searcher: ProductSearcher
) {

    operator fun invoke(category: String?, maxPrice: Int?): List<Product> {
        val filters = listOfNotNull(
            category?.let {
                ProductSearcher.Filter.Category(it)
            },
            maxPrice?.let {
                ProductSearcher.Filter.MaxPrice(it)
            }
        )

        return searcher.search(filters, limit)
    }
}