package org.jforestello.mytheresa_interview.usecase.discount_calculator

import org.jforestello.mytheresa_interview.domain.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DiscountsTest {

    @Test
    fun `test categoryDiscount, given boots category, then applies discount`() {
        val product = PRODUCT.copy(category = "boots")
        val expected = 700 to 30

        val actual = categoryDiscount(product)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test categoryDiscount, given any category, then does not apply the discount`() {
        val product = PRODUCT
        val expected = product.price to 0

        val actual = categoryDiscount(product)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test skuDiscount, given sku 3, then applies discount`() {
        val product = PRODUCT.copy(sku = "000003")
        val expected = 850 to 15

        val actual = skuDiscount(product)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test skuDiscount, given any sku, then does not apply the discount`() {
        val product = PRODUCT
        val expected = product.price to 0

        val actual = skuDiscount(product)

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        private val PRODUCT = Product(
            sku = "00001",
            name = "name",
            category = "category",
            price = 1000
        )
    }
}
