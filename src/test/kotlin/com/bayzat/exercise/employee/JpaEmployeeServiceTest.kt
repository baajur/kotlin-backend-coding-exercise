package com.bayzat.exercise.employee

import com.bayzat.exercise.company.*
import com.bayzat.exercise.constant.Gender
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(InternalEmployeeConfig::class))
@DataJpaTest
class JpaEmployeeServiceTest {

    @Autowired
    lateinit var service: EmployeeService
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Test
    fun retrieveEmployee() {
    }

    @Test
    fun retrieveEmployees() {
    }

    @Test
    fun addEmployee() {
    }

    @Test
    fun updateEmployee() {
        val companyWithEmployees = companyRepository.save( Company(companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))

        val employee1 = Employee(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.00,
                company = companyWithEmployees
        )

        companyWithEmployees.employees = listOf<Employee>(employee1)
        val createdEmployee = employeeRepository.save(employee1)

        System.out.println("*********" + createdEmployee.company.companyId)

        val result = service.updateEmployee(createdEmployee.employeeId!!,
                UpdateEmployeeDto(employeeName  = "company1",
                        phoneNumber = "00000", salary = 15.9,
                        company =createdEmployee.company,
                        gender = Gender.FEMALE,
                        dateOfBirth = LocalDate.of(1990, 8, 22)))

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result?.employeeId).isEqualTo(createdEmployee.employeeId!!)
        Assertions.assertThat(result?.employeeName).isEqualTo("company1")
        Assertions.assertThat(result?.salary).isEqualTo(15.9)
    }

    @Test
    fun deleteEmployee() {
    }

    @Test
    fun getEmployeeRepo() {
    }
}