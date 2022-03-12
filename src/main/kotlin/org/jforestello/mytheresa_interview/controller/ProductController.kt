package org.jforestello.mytheresa_interview.controller

import org.jforestello.mytheresa_interview.controller.model.ProductResponse
import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val discountCalculator: DiscountCalculator,
    private val productsProvider: ProductsProvider
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
}
