package com.bayzat.exercise.employee

import com.bayzat.exercise.company.Company
import com.bayzat.exercise.company.CompanyRepository
import com.bayzat.exercise.company.CompanyService
import com.bayzat.exercise.employee.Employee
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(EmployeeRepository::class, CompanyRepository::class))
@EntityScan(basePackageClasses = arrayOf(Company::class, Employee::class))
@ComponentScan(basePackageClasses = arrayOf(EmployeeService::class, CompanyService::class))
@EnableTransactionManagement
internal class InternalEmployeeConfig
