package org.jforestello.mytheresa_interview.domain.contract

import org.jforestello.mytheresa_interview.domain.Product

interface ProductRepository {

    fun search(filters: List<Filter>, limit: Int): List<Product>

    sealed class Filter private constructor() {
        data class Category(private val filter: String): Filter() {
            override fun filter(product: Product) = product.category == filter
        }

        data class MaxPrice(private val filter: Int): Filter() {
            override fun filter(product: Product) = product.price <= filter
        }

        abstract fun filter(product: Product): Boolean
    }
}

fun List<ProductRepository.Filter>.apply(element: Product): Boolean {
    return this.all {
        it.filter(element)
    }
}

fun List<ProductRepository.Filter>.apply(elements: List<Product>): List<Product> {
    return elements.filter {
        this.apply(it)
    }
}
