package com.bayzat.exercise.domain

import com.bayzat.exercise.constant.Gender
import com.fasterxml.jackson.annotation.*
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "EMPLOYEE")
data class Employee(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "EMPLOYEE_ID", updatable = false, nullable = false)
        val employeeId: Long ?= null,

        val employeeName: String = "",

        @Column(name = "PHONE_NUMBER")
        val phoneNumber: String = "",

        @Enumerated(EnumType.STRING)
        @Column(name = "GENDER")
        val gender: Gender,

        @Column(name = "DATE_OF_BIRTH")
        val dateOfBirth: LocalDate? = null,

        @Column(name = "SALARY")
        val salary: Double = 0.toDouble(),

        @ManyToOne
        @JoinColumn(name = "company", referencedColumnName = "COMPANY_ID", nullable = false, updatable = false)
       // @JsonView(Views.CompanyView::class)
        @JsonManagedReference
        var company: Company,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "employee")
        @JsonBackReference
        var dependant: List<Dependant>? = null

)