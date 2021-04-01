package com.demmage.elvl.controller

import com.demmage.elvl.service.exception.BadQuotationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdviser {

    @ExceptionHandler(value = [(BadQuotationException::class)])
    fun handleBadQuotationException(e: BadQuotationException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

}