package com.bayzat.exercise.company

import com.bayzat.exercise.employee.Employee
import com.fasterxml.jackson.annotation.*
import javax.persistence.*

/**
 * A company entity
 * @author Kamal
 *
 */
@Entity
@Table(name = "COMPANY")
data class Company(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "COMPANY_ID", updatable = false, nullable = false)
        val companyId: Long? = null,

        @Column(name = "COMPANY_NAME")
        val companyName: String = "",

        @Embedded
        val address: Address,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "company")
        @JsonBackReference
        var employees: List<Employee> = emptyList()
) {


    fun toDto(): CompanyDto = CompanyDto(
            companyId = this.companyId!!,
            companyName = this.companyName,
            address = this.address,
            employees = this.employees)

    companion object {
        /**
         * resources that exist all have ID's, GET, PUT, DELETE can use.
         */
        fun fromDto(dto: CompanyDto) = Company(
                companyId = dto.companyId,
                companyName = dto.companyName,
                address = dto.address,
                employees = dto.employees)

        //POST: creating company does not need Id.
        fun fromDto(dto: CreateCompanyDto) = Company(
                companyName = dto.companyName,
                address = dto.address,
                employees = dto.employees)

        //PUT: updating company entity needs and Id to exist
        fun fromDto(dto: UpdateCompanyDto, defaultCompany: Company) = Company(
                companyId = defaultCompany.companyId!!,
                companyName = dto.companyName ?: defaultCompany.companyName,
                address = dto.address ?: defaultCompany.address,
                employees = dto.employees ?: defaultCompany.employees)
    }
}