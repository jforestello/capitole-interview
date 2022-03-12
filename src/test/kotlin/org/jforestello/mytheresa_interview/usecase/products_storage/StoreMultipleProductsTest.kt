package org.jforestello.mytheresa_interview.usecase.products_storage

import io.mockk.coVerifyAll
import io.mockk.mockk
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.ProductsStorage
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StoreMultipleProductsTest {
    private lateinit var repository: ProductRepository
    private lateinit var instance: ProductsStorage

    @BeforeEach
    fun setup() {
        repository = mockk(relaxed = true)
        instance = StoreMultipleProducts(
            repository = repository
        )::invoke
    }

    @Test
    fun `test invoke, given four products, then they are passed to repository`() {

        instance(PRODUCTS)

        coVerifyAll {
            repository.save(eq(PRODUCT))
            repository.save(eq(PRODUCT_2))
            repository.save(eq(PRODUCT_3))
            repository.save(eq(PRODUCT_4))
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