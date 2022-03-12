package org.jforestello.mytheresa_interview.usecase.discount_calculator

import java.util.stream.Stream
import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HigherDiscountCalculatorTest {

    @ParameterizedTest(name = "with {0} discount")
    @MethodSource("getDiscounts")
    fun `test invoke, applies discounts`(name: String, discounts: List<DiscountCalculator>, expectedFinal: Int, expectedRate: Int) {
        val calculator = HigherDiscountCalculator(discounts = discounts)

        val (actualFinal, actualRate) = calculator(PRODUCT)

        Assertions.assertAll(
            "check discount applied",
            { Assertions.assertEquals(expectedFinal, actualFinal) },
            { Assertions.assertEquals(expectedRate, actualRate) },
        )
    }

    companion object {
        private val PRODUCT = Product(
            sku = "00001",
            name = "name",
            category = "category",
            price = 1000
        )

        private fun mockDiscount(discount: Int): DiscountCalculator = {
            it.price - it.price * discount / 100 to discount
        }

        @JvmStatic
        fun getDiscounts(): Stream<Arguments> = Stream.of(
            Arguments.of("0%", listOf(mockDiscount(0)), 1000, 0),
            Arguments.of("15%", listOf(mockDiscount(15)), 850, 15),
            Arguments.of("30%", listOf(mockDiscount(30)), 700, 30),
            Arguments.of("30%", listOf(
                mockDiscount(30),
                mockDiscount(25),
                mockDiscount(20),
                mockDiscount(15),
                mockDiscount(10),
                mockDiscount(5)
            ), 700, 30),
        )
    }
}