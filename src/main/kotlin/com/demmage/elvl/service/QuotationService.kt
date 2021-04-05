package com.demmage.elvl.service

import com.demmage.elvl.domain.Elvl
import com.demmage.elvl.domain.Quotation
import com.demmage.elvl.repo.ElvlRepo
import com.demmage.elvl.repo.QuotationRepo
import com.demmage.elvl.service.exception.BadQuotationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuotationService @Autowired constructor(
    val quotationRepo: QuotationRepo,
    val elvlRepo: ElvlRepo
) {

    fun acceptQuotation(quotation: Quotation) {
        if (quotation.bid >= quotation.ask)
            throw BadQuotationException("Bid must be less than ask")
        else if (quotation.isin.length != 12)
            throw BadQuotationException("Isin length must be equal to 12 characters")
        else if (quotation.ask == 0F && quotation.bid == 0F) {
            throw BadQuotationException("Bid and ask can't be empty")
        }

        val elvlFromDb: Elvl? = elvlRepo.findByIsin(quotation.isin)
        if (elvlFromDb == null) {
            val value: Float = when {
                quotation.bid != 0F -> quotation.bid
                else -> quotation.ask
            }
            elvlRepo.save(Elvl(quotation.isin, value))
        } else {
            when {
                quotation.bid > elvlFromDb.value && quotation.bid != 0F -> elvlFromDb.value = quotation.bid
                quotation.ask < elvlFromDb.value && quotation.ask != 0F -> elvlFromDb.value = quotation.ask
                quotation.bid != 0F -> elvlFromDb.value = quotation.bid
                else -> elvlFromDb.value = quotation.ask
            }
            elvlRepo.save(elvlFromDb)
        }
        quotationRepo.save(quotation)
    }

    fun getElvlByIsin(isin: String): Elvl? {
        return elvlRepo.findByIsin(isin)
    }
}