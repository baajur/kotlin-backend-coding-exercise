package com.bayzat.exercise.employee

import com.bayzat.exercise.company.Company
import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.employee.Employee
import java.time.LocalDate
import javax.validation.constraints.NotEmpty

data class EmployeeDto(
        val employeeId: Long = 0,
        val employeeName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val salary: Double = 0.toDouble(),
        val company: Company
)


data class CreateEmployeeDto(
        val employeeName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val salary: Double = 0.toDouble(),
        var company: Company
)

data class UpdateEmployeeDto(
        val employeeName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val salary: Double = 0.toDouble(),
        val company: Company
)



