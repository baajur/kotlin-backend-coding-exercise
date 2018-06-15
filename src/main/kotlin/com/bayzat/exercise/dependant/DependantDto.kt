package com.bayzat.exercise.dependant

import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.constant.Relation
import com.bayzat.exercise.employee.Employee
import java.time.LocalDate

data class DependantDto(
        val dependantId: Long = 0,
        val dependantName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val relation: Relation,
        val employee: Employee
)


data class CreateDependantDto(
        val dependantName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val relation: Relation,
        val employee: Employee
)

data class UpdateDependantDto(
        val dependantName: String = "",
        val phoneNumber: String = "",
        val gender: Gender,
        val dateOfBirth: LocalDate,
        val relation: Relation,
        val employee: Employee
)



