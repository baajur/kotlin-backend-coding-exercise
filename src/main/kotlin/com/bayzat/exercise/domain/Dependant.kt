package com.bayzat.exercise.domain

import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.constant.Relation
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "DEPENDANT")
data class Dependant (

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "DEPENDANT_ID", updatable = false, nullable = false)
        val dependantId: Long? = null,

        @Column(name = "NAME")
        val dependantName: String = "",

        @Column(name = "PHONE_NUMBER")
        val phoneNumber: String = "",

        @Column(name = "GENDER")
        @Enumerated(EnumType.STRING)
        val gender: Gender,

        @Column(name = "DATE_OF_BIRTH")
        val dateOfBirth: LocalDate? = null,

        @Column(name = "RELATION")
        @Enumerated(EnumType.STRING)
        val relation: Relation,



        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "employee", referencedColumnName = "EMPLOYEE_ID")
        val employee: Employee

)
