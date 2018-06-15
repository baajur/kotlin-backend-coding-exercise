package com.bayzat.exercise.dependant

import com.bayzat.exercise.company.Company
import com.bayzat.exercise.company.CompanyRepository
import com.bayzat.exercise.company.CompanyService
import com.bayzat.exercise.employee.Employee
import com.bayzat.exercise.employee.EmployeeRepository
import com.bayzat.exercise.employee.EmployeeService
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(DependantRepository::class, EmployeeRepository::class))
@EntityScan(basePackageClasses = arrayOf(Dependant::class, Employee::class, Company::class))
@ComponentScan(basePackageClasses = arrayOf(DependantService::class, EmployeeService::class))
@EnableTransactionManagement
internal class InternalDependantConfig
