package org.jforestello.mytheresa_interview.domain.contract

import java.util.stream.Stream
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher.Filter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ProductSearcherTest {

    @ParameterizedTest(name = "with {0} filters")
    @MethodSource("getFilters")
    fun `test Filter, filter products`(name: String, filters: List<Filter>, expected: List<Product>) {

        val actual = filters.apply(PRODUCTS)

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        private val PRODUCT = Product(
            sku = "000001",
            name = "name",
            category = "cat1",
            price = 1000
        )
        private val PRODUCT_2 = PRODUCT.copy(
            category = "cat2",
        )
        private val PRODUCT_3 = PRODUCT.copy(
            price = 500
        )
        private val PRODUCT_4 = PRODUCT_2.copy(
            price = 500
        )
        private val PRODUCTS = listOf(
            PRODUCT, PRODUCT_2, PRODUCT_3, PRODUCT_4
        )
        @JvmStatic
        fun getFilters(): Stream<Arguments> = Stream.of(
            Arguments.of("category", listOf(
                Filter.Category(PRODUCT.category)
            ), listOf(PRODUCT, PRODUCT_3)),
            Arguments.of("maxPrice", listOf(
                Filter.MaxPrice(PRODUCT_3.price)
            ), listOf(PRODUCT_3, PRODUCT_4)),
            Arguments.of("both", listOf(
                Filter.Category(PRODUCT.category),
                Filter.MaxPrice(PRODUCT_3.price)
            ), listOf(PRODUCT_3)),
        )
    }
}