package com.bayzat.exercise.company

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


@Repository
interface CompanyRepository : JpaRepository<Company, Long>{
    fun deleteByCompanyId(companyId: Long): Long?
}