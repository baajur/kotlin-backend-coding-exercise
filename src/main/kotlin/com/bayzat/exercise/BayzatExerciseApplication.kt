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
    fun init(): CommandLineRunner {
        return CommandLineRunner {
            LOGGER.log(Level.INFO, "******INITIALIZATION******")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(BayzatExerciseApplication::class.java, *args)
}


