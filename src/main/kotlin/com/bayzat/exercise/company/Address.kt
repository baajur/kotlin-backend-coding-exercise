package com.bayzat.exercise.company

import com.fasterxml.jackson.annotation.JsonView
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Address(

        @Column(name = "CITY")
        val city: String = "",

        @Column(name = "COUNTRY")
        val country: String = ""

) : Serializable