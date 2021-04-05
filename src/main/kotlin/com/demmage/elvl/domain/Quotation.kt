package com.demmage.elvl.domain

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@Table(name = "quotations")
@NoArgsConstructor
data class Quotation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    @JsonProperty("isin")
    @Column(length = 12)
    var isin: String,
    @JsonProperty("bid")
    var bid: Float,
    @JsonProperty("ask")
    var ask: Float
)