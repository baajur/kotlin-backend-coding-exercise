package com.bayzat.exercise.employee

import com.bayzat.exercise.company.Company
import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.dependant.Dependant
import com.fasterxml.jackson.annotation.*
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "EMPLOYEE")
data class Employee(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "EMPLOYEE_ID", updatable = false, nullable = false)
        val employeeId: Long? = null,

        val employeeName: String = "",

        @Column(name = "PHONE_NUMBER")
        val phoneNumber: String = "",

        @Enumerated(EnumType.STRING)
        @Column(name = "GENDER")
        val gender: Gender,

        @Column(name = "DATE_OF_BIRTH")
        val dateOfBirth: LocalDate,

        @Column(name = "SALARY")
        val salary: Double = 0.toDouble(),

        @ManyToOne
        @JoinColumn(name = "company", referencedColumnName = "COMPANY_ID", updatable = false)
        @JsonManagedReference
        val company: Company,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "employee")
        @JsonBackReference
        val dependants: List<Dependant>? = null

) {

    override fun toString(): String {
        return "Employee(employeeId=$employeeId, employeeName='$employeeName', dateOfBirth=$dateOfBirth, salary=$salary , company=${company?.companyId}, dependants=${dependants?.map { it.dependantId }})"

    }

    fun toDto(): EmployeeDto = EmployeeDto(
            employeeId = this.employeeId!!,
            employeeName = this.employeeName,
            phoneNumber = this.phoneNumber,
            gender = this.gender,
            dateOfBirth = this.dateOfBirth,
            salary = this.salary,
            company = this.company,
            dependants = this.dependants
    )



    companion object {

        fun fromDto(dto: EmployeeDto) = Employee(
                employeeId = dto.employeeId,
                employeeName = dto.employeeName,
                phoneNumber = dto.phoneNumber,
                gender = dto.gender,
                dateOfBirth = dto.dateOfBirth,
                salary = dto.salary,
                company = dto.company,
                dependants = dto.dependants
        )

        fun fromDto(dto: CreateEmployeeDto) = Employee(
                employeeName = dto.employeeName,
                phoneNumber = dto.phoneNumber,
                gender = dto.gender,
                dateOfBirth = dto.dateOfBirth,
                salary = dto.salary,
                company = dto.company,
                dependants = dto.dependants

        )

        fun fromDto(dto: UpdateEmployeeDto, defaultEmployee: Employee) = Employee(
                employeeId = defaultEmployee.employeeId!!,
                employeeName = dto.employeeName ?: defaultEmployee.employeeName,
                phoneNumber = dto.phoneNumber ?: defaultEmployee.phoneNumber,
                gender = dto.gender ?: defaultEmployee.gender,
                dateOfBirth = dto.dateOfBirth ?: defaultEmployee.dateOfBirth,
                salary = dto.salary ?: defaultEmployee.salary,
                company = dto.company ?: defaultEmployee.company,
                dependants = dto.dependants ?: defaultEmployee.dependants
        )

    }


}