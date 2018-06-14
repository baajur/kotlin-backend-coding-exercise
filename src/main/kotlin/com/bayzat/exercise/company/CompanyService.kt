package com.bayzat.exercise.company

interface CompanyService {
    fun retrieveCompany(companyId: Long): CompanyDto?

    fun retrieveCompanies(): List<CompanyDto>

    fun addCompany(company: CreateCompanyDto): CompanyDto

    fun updateCompany(companyId: Long, company: UpdateCompanyDto): CompanyDto?

    fun deleteCompany(companyId: Long): Long?
}