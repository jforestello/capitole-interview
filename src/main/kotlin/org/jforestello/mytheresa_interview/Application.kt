package org.jforestello.mytheresa_interview

import org.jforestello.mytheresa_interview.domain.DiscountCalculator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	@Bean
	fun discountCalculator(): DiscountCalculator = {
		1234 to "%30"
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
