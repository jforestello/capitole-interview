/**
 * this file handles the injection of use cases
**/
package org.jforestello.mytheresa_interview.usecase

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.jforestello.mytheresa_interview.usecase.discount_calculator.HigherDiscountCalculator
import org.jforestello.mytheresa_interview.usecase.discount_calculator.categoryDiscount
import org.jforestello.mytheresa_interview.usecase.discount_calculator.skuDiscount
import org.jforestello.mytheresa_interview.usecase.products_provider.ProductsProviderWithLimit

fun getProductsProvider(limit: Int, repository: ProductRepository): ProductsProvider {
    return ProductsProviderWithLimit(
        limit = limit,
        repository = repository,
    )::invoke
}

fun getDiscountCalculator(): DiscountCalculator {
    return HigherDiscountCalculator(
        listOf(
            ::categoryDiscount,
            ::skuDiscount
        )
    )::invoke
}
