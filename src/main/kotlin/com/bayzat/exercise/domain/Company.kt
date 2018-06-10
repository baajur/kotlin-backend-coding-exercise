package com.bayzat.exercise.domain

import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * A company entity
 * @author Kamal
 *
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "companyId")
@Entity
@Table(name = "company")
//@NamedEntityGraph(name = "fetch.company.employees", attributeNodes = { @NamedAttributeNode("employees") })

data class Company(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "COMPANY_ID", updatable = false, nullable = false)
        val companyId: Long = 0,

        @Column(name = "COMPANY_NAME")
        @get: NotBlank
        val companyName: String = ""

        )