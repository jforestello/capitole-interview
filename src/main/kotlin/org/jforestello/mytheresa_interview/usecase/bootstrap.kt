/**
 * this file handles the injection of use cases
**/
package org.jforestello.mytheresa_interview.usecase

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.usecase.discount_calculator.HigherDiscountCalculator
import org.jforestello.mytheresa_interview.usecase.discount_calculator.categoryDiscount
import org.jforestello.mytheresa_interview.usecase.discount_calculator.skuDiscount

fun getDiscountCalculator(): DiscountCalculator {
    return HigherDiscountCalculator(
        listOf(
            ::categoryDiscount,
            ::skuDiscount
        )
    )::invoke
}
