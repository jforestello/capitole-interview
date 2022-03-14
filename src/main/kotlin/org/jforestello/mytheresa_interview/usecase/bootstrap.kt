/**
 * this file handles the injection of use cases
**/
package org.jforestello.mytheresa_interview.usecase

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.ProductsSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher
import org.jforestello.mytheresa_interview.usecase.discount_calculator.HigherDiscountCalculator
import org.jforestello.mytheresa_interview.usecase.discount_calculator.categoryDiscount
import org.jforestello.mytheresa_interview.usecase.discount_calculator.skuDiscount
import org.jforestello.mytheresa_interview.usecase.products_provider.ProductsProviderWithLimit
import org.jforestello.mytheresa_interview.usecase.products_storage.StoreMultipleProducts

fun getProductsSaver(
    saver: ProductSaver,
): ProductsSaver {
    return StoreMultipleProducts(
        saver = saver
    )::invoke
}

fun getProductsProvider(
    limit: Int,
    searcher: ProductSearcher,
): ProductsProvider {
    return ProductsProviderWithLimit(
        limit = limit,
        searcher = searcher,
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
