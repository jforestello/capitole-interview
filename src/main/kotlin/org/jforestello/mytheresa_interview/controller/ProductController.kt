package org.jforestello.mytheresa_interview.controller

import org.jforestello.mytheresa_interview.controller.model.ProductRequest
import org.jforestello.mytheresa_interview.controller.model.ProductResponse
import org.jforestello.mytheresa_interview.controller.model.toDomain
import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.ProductsSaver
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val discountCalculator: DiscountCalculator,
    private val productsProvider: ProductsProvider,
    private val productsSaver: ProductsSaver
) {

    @GetMapping("/products")
    fun getProducts(
        @RequestParam(name = "category", required = false) category: String?,
        @RequestParam(name = "priceLessThan", required = false) maxPrice: Int?,
    ): List<ProductResponse> {
        return productsProvider(category, maxPrice).map {
            ProductResponse(it, discountCalculator)
        }
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun saveProducts(
        @RequestBody productsBody: List<ProductRequest>
    ): List<ProductResponse> {
        val products = productsBody.toDomain()

        productsSaver(products)

        return products.map {
            ProductResponse(it, discountCalculator)
        }
    }
}
