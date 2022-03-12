package org.jforestello.mytheresa_interview.infrastructure.product

import kotlinx.coroutines.runBlocking
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductStorageTest {
    private val storage = ProductStorage()

    @BeforeEach
    fun setup() {
        runBlocking {
            storage.create(PRODUCT)
        }
    }

    @Test
    fun `test create, then search has two elements`() {
        val newProduct = PRODUCT.copy(
            sku = "000002",
            name = "test2",
        )
        val expected = listOf(
            PRODUCT, newProduct
        )

        runBlocking {
            storage.save(newProduct)
        }

        val actual = storage.search(emptyList(), LIMIT)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test search, with no filters, then returns list`() {
        val expected = listOf(PRODUCT)

        val actual = storage.search(listOf(), LIMIT)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test search, with matching filter, then returns list`() {
        val expected = listOf(PRODUCT)

        val actual = storage.search(listOf(
            ProductRepository.Filter.Category(PRODUCT.category)
        ), LIMIT)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test search, with no matching filter, then returns empty list`() {
        val expected = listOf(PRODUCT)

        val actual = storage.search(listOf(
            ProductRepository.Filter.Category("category")
        ), LIMIT)

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        private val PRODUCT = Product(
            "000003", "test", "boots", 12345
        )
        private const val LIMIT = 5
    }
}
