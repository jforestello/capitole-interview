package org.jforestello.mytheresa_interview.infrastructure.product.model

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.infrastructure.model.Record
import org.jforestello.mytheresa_interview.infrastructure.model.RecordID

class ProductRecord(
    id: RecordID,
    val sku: String,
    val name: String,
    val category: String,
    val price: Int,
): Record<Product>(
    id = id
) {

    constructor(domain: Product, id: RecordID): this(
        id = id,
        sku = domain.sku,
        name = domain.name,
        category = domain.category,
        price = domain.price,
    )

    override fun toDomain(): Product {
        return Product(
            sku = sku,
            name = name,
            category = category,
            price = price,
        )
    }
}
