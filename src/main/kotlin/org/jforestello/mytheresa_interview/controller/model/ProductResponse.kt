package org.jforestello.mytheresa_interview.controller.model

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.Product

private const val DEFAULT_CURRENCY = "EUR"

private fun calculatePrice(product: Product, calculator: DiscountCalculator): Price {
    val (finalPrice, discountRate) = calculator(product)

    return Price(
        original = product.price,
        final = finalPrice,
        discountPercentage = if (discountRate == 0) {
            null
        } else {
            "${discountRate}%"
        },
        currency = DEFAULT_CURRENCY
    )
}

data class ProductResponse(
    val sku: String,
    val name: String,
    val category: String,
    val price: Price,
) {
    constructor(product: Product, calculator: DiscountCalculator): this(
        sku = product.sku,
        name = product.name,
        category = product.category,
        price = calculatePrice(product, calculator)
    )
}

data class Price(
    val original: Int,
    val final: Int,
    val discountPercentage: String?,
    val currency: String
)
