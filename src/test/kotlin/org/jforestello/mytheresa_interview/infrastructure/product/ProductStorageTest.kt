package org.jforestello.mytheresa_interview.infrastructure.product

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ProductStorageTest {
    private val storage = ProductStorage()

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
