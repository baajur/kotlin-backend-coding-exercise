package com.bayzat.exercise

import com.bayzat.exercise.company.*
import com.bayzat.exercise.constant.Gender.*
import com.bayzat.exercise.constant.Relation.*
import com.bayzat.exercise.dependant.Dependant
import com.bayzat.exercise.employee.CreateEmployeeDto
import com.bayzat.exercise.employee.Employee
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.time.LocalDate
import java.util.logging.Level
import java.util.logging.Logger


@SpringBootApplication
class BayzatExerciseApplication {

    private val LOGGER = Logger.getLogger(BayzatExerciseApplication::class.qualifiedName)

    @Bean
    open fun init(companyRepository: CompanyRepository): CommandLineRunner {
        return CommandLineRunner {
            LOGGER.log(Level.INFO, "******INSERTING DUMMY DATA******")

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

            val dependant1 = Dependant(
                    dependantName = "John",
                    dateOfBirth = LocalDate.of(1990, 6, 6),
                    gender = MALE,
                    phoneNumber = "+971555960195",
                    relation = FATHER,
                    employee = employee1
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


