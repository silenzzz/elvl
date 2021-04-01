package com.demmage.elvl.domain

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@Table(name = "elvls")
@NoArgsConstructor
data class Elvl (
        @Id
        @Column(unique = true)
        val isin: String,
        var value: Float
)