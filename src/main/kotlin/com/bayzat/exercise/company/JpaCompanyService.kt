package com.bayzat.exercise.company

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class JpaCompanyService(val companyRepo: CompanyRepository) : CompanyService {

    override fun retrieveCompany(companyId: Long): CompanyDto? {
        return companyRepo.findById(companyId).map { existingCompany ->
            existingCompany.toDto()
        }.orElse(null)
    }

    override fun retrieveCompanies(): List<CompanyDto> {
        return companyRepo.findAll().map { it.toDto() }
    }

    override fun addCompany(company: CreateCompanyDto): CompanyDto {
        return companyRepo.save(Company.fromDto(company)).toDto()
    }

    override fun updateCompany(companyId: Long, company: UpdateCompanyDto): CompanyDto? {
        return companyRepo.findById(companyId).map { currentCompany ->
            companyRepo.save(Company.fromDto(company, currentCompany)).toDto()
        }.orElse(null)
    }


    override fun deleteCompany(companyId: Long): Long? {
        return companyRepo.findById(companyId).map { currentCompany ->
                companyRepo.deleteByCompanyId(companyId)
        }.orElse(null)
    }
}