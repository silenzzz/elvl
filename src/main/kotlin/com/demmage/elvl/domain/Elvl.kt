package com.demmage.elvl.domain

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Data
@Entity
@Table(name = "elvls")
@NoArgsConstructor
data class Elvl(
    @Id
    @Column(unique = true)
    val isin: String,
    var value: Float
)