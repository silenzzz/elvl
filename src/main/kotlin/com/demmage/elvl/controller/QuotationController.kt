package com.demmage.elvl.controller

import com.demmage.elvl.domain.Elvl
import com.demmage.elvl.domain.Quotation
import com.demmage.elvl.repo.QuotationRepo
import com.demmage.elvl.service.QuotationService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class QuotationController @Autowired constructor(
        val quotationService: QuotationService
) {

    @PostMapping
    fun acceptQuotation(@RequestBody quotation: Quotation) : ResponseEntity<String> {
        quotationService.acceptQuotation(quotation)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("{isin}")
    fun getElvlByIsin(@PathVariable isin:String): ResponseEntity<Elvl> {
        val quotation = quotationService.getElvlByIsin(isin)
        return if (quotation != null) {
            ResponseEntity(quotation, HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

}