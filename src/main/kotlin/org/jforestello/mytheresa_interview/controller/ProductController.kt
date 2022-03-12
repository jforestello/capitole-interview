package org.jforestello.mytheresa_interview.controller

import org.jforestello.mytheresa_interview.controller.model.ProductResponse
import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val discountCalculator: DiscountCalculator
) {

    @GetMapping("/products")
    fun getProducts(): List<ProductResponse> {
        return listOf(
            ProductResponse(Product(
                "000003", "test", "boots", 12345
            ), discountCalculator)
        )
    }
}
