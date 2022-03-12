package org.jforestello.mytheresa_interview.infrastructure.model

abstract class Record<T>(
    val id: RecordID
) {
    abstract fun toDomain(): T
}

typealias RecordID = Int
