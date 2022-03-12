package org.jforestello.mytheresa_interview.controller.model.error

class BadRequestException(
    override val message: String
) : Throwable()