package com.ipsoft.restspringbootkotlinapi.exceptions

import java.util.Date
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class CustomizedResponseEntityHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse(ex.message))

    @ExceptionHandler(UnsupportedMathOperatorException::class)
    fun handleUnsupportedMathOperatorException(
        ex: UnsupportedMathOperatorException,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse("Invalid math operator"))
}