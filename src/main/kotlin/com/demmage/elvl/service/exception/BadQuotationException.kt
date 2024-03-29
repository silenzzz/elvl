package com.demmage.elvl.service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadQuotationException(message: String) : RuntimeException(message) {
}