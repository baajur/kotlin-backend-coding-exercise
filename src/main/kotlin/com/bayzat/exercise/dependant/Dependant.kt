package com.bayzat.exercise.dependant

import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.constant.Relation
import com.bayzat.exercise.employee.Employee
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "DEPENDANT")
data class Dependant (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "DEPENDANT_ID", updatable = false, nullable = false)
        val dependantId: Long? = null,

        @Column(name = "DEPENDANT_NAME")
        val dependantName: String = "",

        @Column(name = "PHONE_NUMBER")
        val phoneNumber: String = "",

        @Column(name = "GENDER")
        @Enumerated(EnumType.STRING)
        val gender: Gender,

        @Column(name = "DATE_OF_BIRTH")
        val dateOfBirth: LocalDate,

        @Column(name = "RELATION")
        @Enumerated(EnumType.STRING)
        val relation: Relation,

        @ManyToOne
        @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", updatable = false)
        @JsonManagedReference
        val employee: Employee

) {

        override fun toString(): String {
                return "Dependant(dependantId=$dependantId, dependantName='$dependantName',gender= $gender, dateOfBirth=$dateOfBirth, relation=$relation , parent=${employee?.employeeId})"

        }

        fun toDto(): DependantDto = DependantDto(
                dependantId = this.dependantId!!,
                dependantName  = this.dependantName,
                phoneNumber = this.phoneNumber,
                gender = this.gender,
                dateOfBirth = this.dateOfBirth,
                relation= this.relation,
                employee = this.employee
        )


        companion object {

                fun fromDto(dto: DependantDto) = Dependant(
                        dependantId = dto.dependantId,
                        dependantName  = dto.dependantName,
                        phoneNumber = dto.phoneNumber,
                        gender = dto.gender,
                        dateOfBirth = dto.dateOfBirth,
                        relation= dto.relation,
                        employee = dto.employee
                )

                fun fromDto(dto: CreateDependantDto) = Dependant(
                        dependantName  = dto.dependantName,
                        phoneNumber = dto.phoneNumber,
                        gender = dto.gender,
                        dateOfBirth = dto.dateOfBirth,
                        relation= dto.relation,
                        employee = dto.employee
                )

                fun fromDto(dto: UpdateDependantDto, defaultDependant: Dependant) = Dependant(
                        dependantId = defaultDependant.dependantId!!,
                        dependantName = dto.dependantName ?: defaultDependant.dependantName,
                        phoneNumber = dto.phoneNumber ?: defaultDependant.phoneNumber,
                        gender = dto.gender ?: defaultDependant.gender,
                        dateOfBirth = dto.dateOfBirth ?: defaultDependant.dateOfBirth,
                        relation = dto.relation ?: defaultDependant.relation,
                        employee = dto.employee ?: defaultDependant.employee
                )

        }
}


