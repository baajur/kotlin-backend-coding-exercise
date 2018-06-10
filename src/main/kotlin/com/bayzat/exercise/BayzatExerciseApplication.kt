package com.bayzat.exercise

import com.bayzat.exercise.domain.Company
import com.bayzat.exercise.repository.CompanyRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.logging.Level
import java.util.logging.Logger


@SpringBootApplication
open class BayzatExerciseApplication {

    private val LOGGER = Logger.getLogger(BayzatExerciseApplication::class.qualifiedName)

    @Bean
    open fun init(companyRepository: CompanyRepository): CommandLineRunner {
        return CommandLineRunner {
            LOGGER.log(Level.INFO, "******INSERTING DUMMY DATA******")
            companyRepository.save(Company(companyName = "Bayzat"))
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(BayzatExerciseApplication::class.java, *args)
}


