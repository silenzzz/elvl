package com.demmage.elvl.controller

import com.demmage.elvl.domain.Quotation
import com.demmage.elvl.service.QuotationService
import com.demmage.elvl.service.exception.BadQuotationException
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
internal class QuotationControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val quotationService: QuotationService
) {

    @Test
    @Order(1)
    fun shouldReturnIsinWithBidValue() {
        quotationService.acceptQuotation(Quotation(101L, "RU000A102XF1", 100.2F, 101.9F))
        mvc.perform(get("/RU000A102XF1"))
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    "{\n" +
                            "    \"isin\": \"RU000A102XF1\",\n" +
                            "    \"value\": 100.2\n" +
                            "}"
                )
            )
    }

    @Test
    @Order(2)
    fun shouldUpdateElvlWithNewBidValue() {
        quotationService.acceptQuotation(Quotation(101L, "RU000A102XF1", 100.5F, 101.9F))
        mvc.perform(get("/RU000A102XF1"))
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    "{\n" +
                            "    \"isin\": \"RU000A102XF1\",\n" +
                            "    \"value\": 100.5\n" +
                            "}"
                )
            )
    }

    @Test
    fun shouldThrowBadQuotationExceptionThenQuotationWithSimilarAskAndBidGiven() {
        assertThrows<BadQuotationException> {
            quotationService.acceptQuotation(Quotation(999L, "RU000N23GXA9", 100.2F, 100.2F))
        }
    }

    @Test
    fun shouldThrowBadQuotationExceptionThenQuotationMissingAskAndBid() {
        assertThrows<BadQuotationException> {
            quotationService.acceptQuotation(Quotation(999L, "RU000N23GXA9", 0F, 0F))
        }
    }
}