package org.jforestello.mytheresa_interview

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.contract.ProductSaver
import org.jforestello.mytheresa_interview.domain.contract.ProductSearcher
import org.jforestello.mytheresa_interview.infrastructure.getProductSaver
import org.jforestello.mytheresa_interview.infrastructure.getProductSearcher
import org.jforestello.mytheresa_interview.usecase.getDiscountCalculator
import org.jforestello.mytheresa_interview.usecase.getProductsProvider
import org.jforestello.mytheresa_interview.usecase.getProductsSaver
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun productSaver() = getProductSaver()

	@Bean
	fun productSearcher() = getProductSearcher()

	@Bean
	fun productsSaver(
		saver: ProductSaver,
	) = getProductsSaver(saver)

	@Bean
	fun productsProvider(
		@Value("\${provider.limit}") limit: Int,
		searcher: ProductSearcher
	): ProductsProvider = getProductsProvider(limit, searcher)

	@Bean
	fun discountCalculator(): DiscountCalculator = getDiscountCalculator()
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
