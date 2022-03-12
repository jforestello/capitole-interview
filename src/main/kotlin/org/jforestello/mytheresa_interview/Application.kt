package org.jforestello.mytheresa_interview

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.jforestello.mytheresa_interview.usecase.getDiscountCalculator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun discountCalculator(): DiscountCalculator = getDiscountCalculator()
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
