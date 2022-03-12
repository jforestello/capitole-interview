package org.jforestello.mytheresa_interview.controller.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import org.jforestello.mytheresa_interview.controller.model.error.BadRequestException
import org.jforestello.mytheresa_interview.domain.Product

data class ProductRequest(
    val sku: String?,
    val name: String?,
    val category: String?,
    val price: Int?,
) {

    @ExperimentalContracts
    private fun validate(sku: String?, name: String?, category: String?, price: Int?) {
        contract {
            returns() implies (sku != null && name != null && category != null && price != null)
        }
        sku ?: throw BadRequestException("invalid sku provided")
        name ?: throw BadRequestException("invalid name provided")
        category ?: throw BadRequestException("invalid category provided")
        price ?: throw BadRequestException("invalid price provided")
    }

    @OptIn(ExperimentalContracts::class)
    fun toDomain(): Product {
        validate(sku, name, category, price)
        return Product(
            sku = sku,
            name = name,
            category = category,
            price = price,
        )
    }
}

fun List<ProductRequest>.toDomain(): List<Product> = this.map(ProductRequest::toDomain)
