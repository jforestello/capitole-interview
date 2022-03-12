package org.jforestello.mytheresa_interview.usecase.discount_calculator

import kotlin.math.roundToInt
import org.jforestello.mytheresa_interview.domain.Product

/**
 * Applies 30% discount to products with category = boots
 **/
fun categoryDiscount(product: Product) = if (product.category == "boots") {
    (product.price * 0.7).roundToInt() to 30
} else {
    product.price to 0
}

/**
 * Applies 15% discount to products with sku = 000003
 **/
fun skuDiscount(product: Product) = if (product.sku == "000003") {
    (product.price * 0.85).roundToInt() to 15
} else {
    product.price to 0
}