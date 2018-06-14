package com.bayzat.exercise

import com.bayzat.exercise.company.*
import com.bayzat.exercise.constant.Gender.*
import com.bayzat.exercise.constant.Relation.*
import com.bayzat.exercise.employee.CreateEmployeeDto
import com.bayzat.exercise.employee.Employee
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDate
import java.util.logging.Level
import java.util.logging.Logger


@SpringBootApplication
open class BayzatExerciseApplication {

    private val LOGGER = Logger.getLogger(BayzatExerciseApplication::class.qualifiedName)

    @Bean
    open fun init(companyRepository: CompanyRepository): CommandLineRunner {
        return CommandLineRunner {
            LOGGER.log(Level.INFO, "******INSERTING DUMMY DATA******")
/*
            //create a company, that has employees, a company can have zero or one employees
            val companyWithEmployees = (Company(
                    companyName = "Bayzat",
                    address = Address("Dubai", country = "UAE")
            ))

            //create an empty company, that has no employees
            val companyWithoutEmployees = (Company(
                    companyName = "Bayzat",
                    address = Address("Dubai", country = "UAE")
            ))
            //create employees and set their company and  properties
            val employee1: Employee = Employee(
                    employeeName = "John",
                    dateOfBirth = LocalDate.of(1990, 6, 6),
                    gender = MALE,
                    phoneNumber = "+971555960195",
                    salary = 1500.00,
                    company = companyWithEmployees
            )

            val employee2: Employee = Employee(
                    employeeName = "Jane",
                    dateOfBirth = LocalDate.of(1991, 6, 6),
                    gender = FEMALE,
                    phoneNumber = "+971555960195",
                    salary = 1300.00,
                    company = companyWithEmployees
            )

            val dependant1: Dependant = Dependant(
                    dependantName = "Dependant1",
                    gender= MALE,
                    relation = FATHER,
                    employee = employee1
            )



            //set the company's employee and save it
            companyWithEmployees.employees = listOf<Employee>(employee1, employee2)
            //employee1.dependant = listOf(dependant1)
            //companyRepository.save(companyWithEmployees)
            //companyRepository.save(companyWithoutEmployees)*/




            val companyWithoutEmployees = Company(companyName = "Bayzat",
                    address = Address(city = "Dubai", country = "UAE"))
            val companyWithEmployees = Company(companyName = "Bayzat",
                    address = Address(city = "Dubai", country = "UAE"))

            val employee1 = Employee(
                    employeeName = "John",
                    dateOfBirth = LocalDate.of(1990, 6, 6),
                    gender = MALE,
                    phoneNumber = "+971555960195",
                    salary = 1500.00,
                    company = companyWithEmployees
            )

            companyWithEmployees.employees = listOf<Employee>(employee1)
            companyRepository.save(companyWithEmployees)
            companyRepository.save(companyWithoutEmployees)

        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(BayzatExerciseApplication::class.java, *args)
}


