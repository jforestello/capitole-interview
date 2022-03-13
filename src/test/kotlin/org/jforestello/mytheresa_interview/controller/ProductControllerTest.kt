package org.jforestello.mytheresa_interview.controller

import org.jforestello.mytheresa_interview.controller.model.Price
import org.jforestello.mytheresa_interview.controller.model.ProductRequest
import org.jforestello.mytheresa_interview.controller.model.ProductResponse
import org.jforestello.mytheresa_interview.domain.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ProductControllerTest {

    @Test
    fun `test getProducts, with no applicable discounts, then ProductResponse is returned`() {
        val expected = listOf(ProductResponse(
            sku = PRODUCT.sku,
            name = PRODUCT.name,
            category = PRODUCT.category,
            price = Price(
                original = PRODUCT.price,
                final = PRODUCT.price,
                discountPercentage = null,
                currency = "EUR"
            )
        ))
        val controller = ProductController(
            { PRODUCT.price to 0 },
            { _, _ -> listOf(PRODUCT) },
            {}
        )

        val actual = controller.getProducts(null, null)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test getProducts, with a discount applied, then ProductResponse is returned`() {
        val expectedPrice = 200
        val expectedPercentage = 80
        val expected = listOf(ProductResponse(
            sku = PRODUCT.sku,
            name = PRODUCT.name,
            category = PRODUCT.category,
            price = Price(
                original = PRODUCT.price,
                final = expectedPrice,
                discountPercentage = "${expectedPercentage}%",
                currency = "EUR"
            )
        ))
        val controller = ProductController(
            { expectedPrice to expectedPercentage },
            { _, _ -> listOf(PRODUCT) },
            {}
        )

        val actual = controller.getProducts(null, null)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test getProducts, given a category and max price, then both are passed to provider`() {
        val expectedCategory = "expected"
        val expectedMaxPrice = 2000
        var actualCategory: String? = null
        var actualMaxPrice: Int? = null
        val controller = ProductController(
            { PRODUCT.price to 0 },
            { cat, price ->
                actualCategory = cat
                actualMaxPrice = price
                listOf(PRODUCT)
            },
            {}
        )

        controller.getProducts(expectedCategory, expectedMaxPrice)

        Assertions.assertAll(
            { Assertions.assertEquals(expectedCategory, actualCategory) },
            { Assertions.assertEquals(expectedMaxPrice, actualMaxPrice) }
        )
    }

    @Test
    fun `test saveProducts, given many valid requests, then everything is passed to provider and returned as product response`() {
        val expected = listOf(
            ProductResponse(
                sku = PRODUCT.sku,
                name = PRODUCT.name,
                category = PRODUCT.category,
                price = Price(
                    original = PRODUCT.price,
                    final = PRODUCT.price,
                    discountPercentage = null,
                    currency = "EUR"
                )
            ),
            ProductResponse(
                sku = PRODUCT_2.sku,
                name = PRODUCT_2.name,
                category = PRODUCT_2.category,
                price = Price(
                    original = PRODUCT_2.price,
                    final = PRODUCT_2.price,
                    discountPercentage = null,
                    currency = "EUR"
                )
            ),
            ProductResponse(
                sku = PRODUCT_3.sku,
                name = PRODUCT_3.name,
                category = PRODUCT_3.category,
                price = Price(
                    original = PRODUCT_3.price,
                    final = PRODUCT_3.price,
                    discountPercentage = null,
                    currency = "EUR"
                )
            ),
            ProductResponse(
                sku = PRODUCT_4.sku,
                name = PRODUCT_4.name,
                category = PRODUCT_4.category,
                price = Price(
                    original = PRODUCT_4.price,
                    final = PRODUCT_4.price,
                    discountPercentage = null,
                    currency = "EUR"
                )
            )
        )
        val expectedProducts = PRODUCTS
        var actualProducts: List<Product>? = null
        val controller = ProductController(
            { it.price to 0 },
            { _, _ -> emptyList() },
            { actualProducts = it }
        )
        val requests = listOf(
            ProductRequest(
                sku = PRODUCT.sku,
                name = PRODUCT.name,
                category = PRODUCT.category,
                price = PRODUCT.price
            ),
            ProductRequest(
                sku = PRODUCT_2.sku,
                name = PRODUCT_2.name,
                category = PRODUCT_2.category,
                price = PRODUCT_2.price
            ),
            ProductRequest(
                sku = PRODUCT_3.sku,
                name = PRODUCT_3.name,
                category = PRODUCT_3.category,
                price = PRODUCT_3.price
            ),
            ProductRequest(
                sku = PRODUCT_4.sku,
                name = PRODUCT_4.name,
                category = PRODUCT_4.category,
                price = PRODUCT_4.price
            ),
        )

        val actual = controller.saveProducts(requests)

        Assertions.assertAll(
            { Assertions.assertEquals(expectedProducts, actualProducts) },
            { Assertions.assertEquals(expected, actual) }
        )
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
