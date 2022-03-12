package org.jforestello.mytheresa_interview.usecase.products_provider

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductsProviderWithLimitTest {
    private lateinit var repository: ProductRepository
    private lateinit var instance: ProductsProvider

    @BeforeEach
    fun setup() {
        repository = mockk(relaxed = true)
        instance = ProductsProviderWithLimit(
            limit = LIMIT,
            repository = repository
        )::invoke
    }

    @Test
    fun `test provider, given no filter, then none is passed to repository`() {
        val expected = listOf(PRODUCT)
        every {
            repository.search(eq(listOf()), eq(LIMIT))
        } returns expected

        val actual = instance(null, null)

        verify { repository.search(eq(listOf()), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given a category filter, then pass filter to repository`() {
        val category = "cat1"
        val expected = listOf(PRODUCT)
        val expectedFilter = ProductRepository.Filter.Category(category)
        every {
            repository.search(eq(listOf(expectedFilter)), eq(LIMIT))
        } returns expected

        val actual = instance(category, null)

        verify { repository.search(eq(listOf(expectedFilter)), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given a maxPrice filter, then pass filter to repository`() {
        val maxPrice = 1000
        val expected = listOf(PRODUCT)
        val expectedFilter = ProductRepository.Filter.MaxPrice(maxPrice)
        every {
            repository.search(eq(listOf(expectedFilter)), eq(LIMIT))
        } returns expected

        val actual = instance(null, maxPrice)

        verify { repository.search(eq(listOf(expectedFilter)), eq(LIMIT)) }
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test provider, given many filters, then pass filters to repository`() {
        val category = "cat1"
        val maxPrice = 1000
        val expected = listOf(PRODUCT)
        val expectedFilters = listOf(
            ProductRepository.Filter.Category(category),
            ProductRepository.Filter.MaxPrice(maxPrice)
        )
        every {
            repository.search(eq(expectedFilters), eq(LIMIT))
        } returns expected

        val actual = instance(category, maxPrice)

        verify { repository.search(eq(expectedFilters), eq(LIMIT)) }
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