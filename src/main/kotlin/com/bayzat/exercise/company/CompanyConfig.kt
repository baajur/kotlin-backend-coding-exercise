package com.bayzat.exercise.company

import com.bayzat.exercise.dependant.Dependant
import com.bayzat.exercise.employee.Employee
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(CompanyRepository::class))
@EntityScan(basePackageClasses = arrayOf(Company::class, Employee::class, Dependant::class))
@ComponentScan(basePackageClasses = arrayOf(CompanyService::class))
@EnableTransactionManagement
internal class InternalCompanyConfig
