package com.bayzat.exercise.company

import com.bayzat.exercise.constant.COMPANIES_PATH
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.logging.Level
import java.util.logging.Logger

@RestController
@RequestMapping(value = COMPANIES_PATH)
class CompanyController(val companyService: CompanyService) {

    val log = Logger.getLogger(CompanyController::class.java.getName())

    @GetMapping
    fun retrieveCompanies(): ResponseEntity<List<CompanyDto>> {
        log.info("Retrieving companies")
        val result = companyService.retrieveCompanies()
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("{companyId}")
    fun retrieveCompany(@PathVariable("companyId") companyId: Long): ResponseEntity<CompanyDto> {
        log.log(Level.INFO, "Retrieving company: {}", companyId)

        val result = companyService.retrieveCompany(companyId)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun addCompany(@RequestBody company: CreateCompanyDto, uriBuilder: UriComponentsBuilder): ResponseEntity<CompanyDto> {
        log.info("Request to add a company")

        val result = companyService.addCompany(company)
        return ResponseEntity
                .created(uriBuilder.path("${COMPANIES_PATH}/{companyId}").buildAndExpand(result.companyId).toUri())
                .body(result)
    }

    @PutMapping("{companyId}")
    fun updateCompany(@PathVariable("companyId") companyId: Long, @RequestBody company: UpdateCompanyDto): ResponseEntity<CompanyDto> {
        log.log(Level.INFO, "Request to update company: {}", companyId)

        val result = companyService.updateCompany(companyId, company)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("{companyId}")
    fun deleteCompanyById(@PathVariable(value = "companyId") companyId: Long): ResponseEntity<Void> {
        val result = companyService.deleteCompany(companyId)
        if (result != null) {
            log.info("CompanyId:" + companyId)
            return ResponseEntity<Void>(HttpStatus.OK)
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }


}