package org.jforestello.mytheresa_interview

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.domain.ProductsProvider
import org.jforestello.mytheresa_interview.domain.contract.ProductRepository
import org.jforestello.mytheresa_interview.infrastructure.getProductRepository
import org.jforestello.mytheresa_interview.usecase.getDiscountCalculator
import org.jforestello.mytheresa_interview.usecase.getProductsProvider
import org.jforestello.mytheresa_interview.usecase.getProductsStorage
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun productRepository() = getProductRepository()

	@Bean
	fun productsStorage(repository: ProductRepository) = getProductsStorage(repository)

	@Bean
	fun productsProvider(
		@Value("\${provider.limit}") limit: Int,
		repository: ProductRepository
	): ProductsProvider = getProductsProvider(limit, repository)

	@Bean
	fun discountCalculator(): DiscountCalculator = getDiscountCalculator()
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
