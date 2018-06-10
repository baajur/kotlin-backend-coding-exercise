package com.bayzat.exercise.controller

import com.bayzat.exercise.domain.Company
import com.bayzat.exercise.repository.CompanyRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class CompanyController (private val companyRepository: CompanyRepository) {

    @GetMapping("/companies")
    fun getAllCompanies(): List<Company> =
            companyRepository.findAll()


    @PostMapping("/companies" )
    fun createNewCompany(@Valid @RequestBody company: Company): Company =
            companyRepository.save(company)

    @GetMapping("/companies/{companyId}")
    fun getCompanyById(@PathVariable(value = "companyId") companyId: Long): ResponseEntity<Company> {
        return companyRepository.findById(companyId).map { company ->
            ResponseEntity.ok(company)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/companies/{companyId}")
    fun updateCompanyById(@PathVariable(value = "companyId") companyId: Long,
                          @Valid @RequestBody newCompany: Company): ResponseEntity<Company> {

        return companyRepository.findById(companyId).map { existingCompany ->
            val updatedCompany: Company = existingCompany
                    .copy(companyName = newCompany.companyName)
            ResponseEntity.ok().body(companyRepository.save(updatedCompany))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/companies/{companyId}")
    fun deleteCompanyById(@PathVariable(value = "companyId") companyId: Long): ResponseEntity<Void> {

        return companyRepository.findById(companyId).map { company  ->
            companyRepository.delete(company)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}