package org.jforestello.mytheresa_interview.usecase.products_storage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jforestello.mytheresa_interview.domain.Product
import org.jforestello.mytheresa_interview.domain.contract.ProductSaver

class StoreMultipleProducts(
    private val saver: ProductSaver
) {

    operator fun invoke(products: List<Product>) {
        products.forEach {
            CoroutineScope(Dispatchers.Default).launch {
                saver.save(it)
            }
        }
    }
}