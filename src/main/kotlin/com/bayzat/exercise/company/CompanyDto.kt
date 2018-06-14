package com.bayzat.exercise.company

import com.bayzat.exercise.employee.Employee
import javax.validation.constraints.NotEmpty

data class CompanyDto(
        val companyId: Long = 0,
        val companyName: String = "",
        val address: Address,
        val employees: List<Employee> = emptyList()
)


data class CreateCompanyDto(
        @NotEmpty val companyName: String = "",
        @NotEmpty val address: Address,
        val employees: List<Employee> = emptyList()
)

data class UpdateCompanyDto(
        @NotEmpty val companyName: String="" ,
        @NotEmpty val address: Address,
        val employees: List<Employee> = emptyList()
)



