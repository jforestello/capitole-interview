package org.jforestello.mytheresa_interview.controller

import java.lang.Exception
import org.jforestello.mytheresa_interview.controller.model.error.ApiException
import org.jforestello.mytheresa_interview.controller.model.error.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(): ResponseEntity<ApiException> {
        return ResponseEntity.internalServerError()
            .body(ApiException(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
            ))
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ResponseEntity<ApiException> {
        return ResponseEntity.badRequest()
            .body(ApiException(
                status = HttpStatus.BAD_REQUEST.value(),
                message = e.message
            ))
    }
}