package org.jforestello.mytheresa_interview.controller

import org.jforestello.mytheresa_interview.controller.model.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/products")
    fun getProducts(): List<ProductResponse> {
        return listOf()
    }
}