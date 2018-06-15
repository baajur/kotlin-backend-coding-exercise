package com.bayzat.exercise.employee

import com.bayzat.exercise.company.*
import com.bayzat.exercise.constant.Gender
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import com.bayzat.exercise.company.Company


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(InternalEmployeeConfig::class))
@DataJpaTest
class JpaEmployeeServiceTest {

    @Autowired
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var employeeService: EmployeeService

    /*
	 * Before every test is important we create a company without a company
	 * there cannot be employees.
	 */
    @Before
    fun setUp() {
        val result = companyService.addCompany(
                CreateCompanyDto(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE")))

        assertThat(result.companyId).isNotNull()
        assertThat(result.companyName).isEqualTo("Bayzat")
        assertThat(result.employees).isEmpty()
    }

    /**
     * should return created entity of employee
     */
    @Test
    fun addEmployee() {
        //get the first company then add employee to the company to perform persist
        val existingCompanies = companyService.retrieveCompanies()
        assertThat(existingCompanies).hasSize(1)
        val existingCompany = Company.fromDto(existingCompanies.first())


        val employee1 = CreateEmployeeDto(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.00,
                company = existingCompany
        )

        existingCompany.employees = listOf<Employee>(Employee.fromDto(employee1))
        val result = employeeService.addEmployee(employee1)

        assertThat(result.employeeId).isNotNull()
        assertThat(result.employeeName).isEqualTo("John")
        assertThat(result.gender).isEqualTo(Gender.MALE)
    }

    /**
     * should retrieve empty list if repository doesn't contain entities
     */
    @Test
    fun retrieveEmployees() {
        assertThat(employeeService.retrieveEmployees()).isEmpty()
    }

    /**
     * should return null if company for companyId doesn't exist
     */
    @Test
    fun retrieveNonExistEmployee() {
        assertThat(employeeService.retrieveEmployee(-99)).isNull()
    }

    /**
     * should map existing employee from repository
     */
    @Test
    fun retrieveEmployee() {
        //get the first company then add employee to the company to perform persist
        val existingCompanies = companyService.retrieveCompanies()
        assertThat(existingCompanies).hasSize(1)
        val existingCompany = Company.fromDto(existingCompanies.first())


        val employee1 = CreateEmployeeDto(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.0,
                company = existingCompany
        )

        existingCompany.employees = listOf<Employee>(Employee.fromDto(employee1))
        val result = employeeService.addEmployee(employee1)

        assertThat(result).isNotNull
        assertThat(result!!.employeeId).isNotNull()
        assertThat(result?.employeeName).isEqualTo("John")
        assertThat(result.salary).isEqualTo(1500.0)
    }

    /**
     * should update existing value
     */
    @Test
    fun updateEmployee() {
        //get the first company then add employee to the company to perform persist
        val existingCompanies = companyService.retrieveCompanies()
        assertThat(existingCompanies).hasSize(1)
        val existingCompany = Company.fromDto(existingCompanies.first())


        val employee1 = CreateEmployeeDto(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.0,
                company = existingCompany
        )

        existingCompany.employees = listOf<Employee>(Employee.fromDto(employee1))
        val createdEmployee = employeeService.addEmployee(employee1)

        val result = employeeService.updateEmployee(createdEmployee.employeeId,
                UpdateEmployeeDto(employeeName = "Suzan",
                        dateOfBirth = LocalDate.of(1990, 6, 6),
                        gender = Gender.FEMALE,
                        phoneNumber = "+971555960195",
                        salary = 1300.0,
                        company = createdEmployee.company))
        assertThat(result).isNotNull
        assertThat(result?.employeeId).isEqualTo(createdEmployee.employeeId)
        assertThat(result?.employeeName).isEqualTo("Suzan")
        assertThat(result?.salary).isEqualTo(1300.0)
        assertThat(result?.gender).isEqualTo(Gender.FEMALE)
    }

    /**
     * should delete existing employee
     */

    @Test
    fun deleteEmployee() {
        //the first part of the code is to create a company then add employee to it
        val existingCompanies = companyService.retrieveCompanies()
        assertThat(existingCompanies).hasSize(1)
        val existingCompany = Company.fromDto(existingCompanies.first())


        val employee1 = CreateEmployeeDto(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.0,
                company = existingCompany
        )

        existingCompany.employees = listOf<Employee>(Employee.fromDto(employee1))
        val createdEmployee = employeeService.addEmployee(employee1)

        //the second part is to fetch inserted employee and then perfrom delete
        val deleteEmployeee = employeeService.retrieveEmployee(createdEmployee.employeeId)
        assertThat(deleteEmployeee).isNotNull
        val result = employeeService.deleteEmployee(deleteEmployeee!!.employeeId)
        assertThat(result).isEqualTo(1)
        assertThat(employeeService.retrieveEmployee(createdEmployee.employeeId)).isNull()

    }

}