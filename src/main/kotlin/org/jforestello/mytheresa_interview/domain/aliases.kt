package org.jforestello.mytheresa_interview.domain

typealias DiscountCalculator = (Product) -> Pair<Int, Int>
typealias ProductsProvider = (String?, Int?) -> List<Product>
typealias ProductsSaver = (List<Product>) -> Unit
