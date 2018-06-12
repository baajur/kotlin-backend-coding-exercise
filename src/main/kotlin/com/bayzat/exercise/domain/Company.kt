package com.bayzat.exercise.domain

import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * A company entity
 * @author Kamal
 *
 */
@Entity
@Table(name = "company")
data class Company(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "COMPANY_ID", updatable = false, nullable = false)
        @JsonSetter(nulls= Nulls.SKIP)
        val companyId: Long ?=  null,

        @Column(name = "COMPANY_NAME")
        @get: NotBlank
        val companyName: String = "",

        @Embedded
        val address: Address,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "company")
        @JsonBackReference
        var employees: List<Employee> = emptyList()

)