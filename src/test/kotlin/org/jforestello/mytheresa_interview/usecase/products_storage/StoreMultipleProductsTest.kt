package org.jforestello.mytheresa_interview.usecase.products_storage

import io.mockk.coVerifyAll
import io.mockk.mockk
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.ProductsSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSaver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StoreMultipleProductsTest {
    private lateinit var saver: ProductSaver
    private lateinit var instance: ProductsSaver

    @BeforeEach
    fun setup() {
        saver = mockk(relaxed = true)
        instance = StoreMultipleProducts(
            saver = saver
        )::invoke
    }

    @Test
    fun `test invoke, given four products, then they are passed to repository`() {

        instance(PRODUCTS)

        coVerifyAll {
            saver.save(eq(PRODUCT))
            saver.save(eq(PRODUCT_2))
            saver.save(eq(PRODUCT_3))
            saver.save(eq(PRODUCT_4))
        }
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
    }
}