package org.jforestello.mytheresa_interview.usecase.discount_calculator

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.Product

class HigherDiscountCalculator(
    private val discounts: List<DiscountCalculator>
) {

    operator fun invoke(product: Product): Pair<Int, Int> {
        var currentRate = 0
        var finalPrice = product.price
        for (calculator in discounts) {
            val (discount, rate) = calculator(product)
            if (rate > currentRate) {
                finalPrice = discount
                currentRate = rate
            }
        }
        return finalPrice to currentRate
    }
}