package com.demmage.elvl.repo

import com.demmage.elvl.domain.Elvl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ElvlRepo : JpaRepository<Elvl, String> {

    fun findByIsin(isin: String): Elvl?

}