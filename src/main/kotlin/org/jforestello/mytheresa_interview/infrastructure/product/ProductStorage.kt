package org.jforestello.mytheresa_interview.infrastructure.product

import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher
import org.jforestello.mytheresa_interview.domain.contract.apply
import org.jforestello.mytheresa_interview.infrastructure.model.InMemoryStorage
import org.jforestello.mytheresa_interview.infrastructure.model.RecordID
import org.jforestello.mytheresa_interview.infrastructure.product.model.ProductRecord

class ProductStorage: ProductSearcher, ProductSaver, InMemoryStorage<Product, ProductRecord>() {

    override suspend fun save(product: Product) {
        create(product)
    }

    override fun search(filters: List<ProductSearcher.Filter>, limit: Int): List<Product> {
        return super.storage.values.mapNotNull {
            val domain = it.toDomain()
            if (filters.apply(domain)) {
                domain
            } else {
                null
            }
        }.take(limit)
    }

    override fun getRecord(domain: Product, id: RecordID): ProductRecord {
        return ProductRecord(domain, id)
    }
}
