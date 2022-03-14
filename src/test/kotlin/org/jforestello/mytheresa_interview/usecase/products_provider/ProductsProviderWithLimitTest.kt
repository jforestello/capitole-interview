package org.jforestello.mytheresa_interview.usecase.products_provider

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductsProviderWithLimitTest {
    private lateinit var searcher: ProductSearcher
    private lateinit var instance: ProductsProvider

    @BeforeEach
    fun setup() {
        searcher = mockk(relaxed = true)
        instance = ProductsProviderWithLimit(
            limit = LIMIT,
            searcher = searcher
        )::invoke
    }

    @Test
    fun `test provider, given no filter, then none is passed to repository`() {
        val expected = listOf(PRODUCT)
        every {
            searcher.search(eq(listOf()), eq(LIMIT))
        } returns expected

        val actual = instance(null, null)

        verify { searcher.search(eq(listOf()), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given a category filter, then pass filter to repository`() {
        val category = "cat1"
        val expected = listOf(PRODUCT)
        val expectedFilter = ProductSearcher.Filter.Category(category)
        every {
            searcher.search(eq(listOf(expectedFilter)), eq(LIMIT))
        } returns expected

        val actual = instance(category, null)

        verify { searcher.search(eq(listOf(expectedFilter)), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given a maxPrice filter, then pass filter to repository`() {
        val maxPrice = 1000
        val expected = listOf(PRODUCT)
        val expectedFilter = ProductSearcher.Filter.MaxPrice(maxPrice)
        every {
            searcher.search(eq(listOf(expectedFilter)), eq(LIMIT))
        } returns expected

        val actual = instance(null, maxPrice)

        verify { searcher.search(eq(listOf(expectedFilter)), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given many filters, then pass filters to repository`() {
        val category = "cat1"
        val maxPrice = 1000
        val expected = listOf(PRODUCT)
        val expectedFilters = listOf(
            ProductSearcher.Filter.Category(category),
            ProductSearcher.Filter.MaxPrice(maxPrice)
        )
        every {
            searcher.search(eq(expectedFilters), eq(LIMIT))
        } returns expected

        val actual = instance(category, maxPrice)

        verify { searcher.search(eq(expectedFilters), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    companion object {
        private val PRODUCT = Product(
            sku = "000001",
            name = "name",
            category = "cat1",
            price = 1000
        )
        private const val LIMIT = 5
    }
}