package com.bayzat.exercise.dependant

import com.bayzat.exercise.company.*
import com.bayzat.exercise.constant.Gender
import com.bayzat.exercise.constant.Relation
import com.bayzat.exercise.employee.CreateEmployeeDto
import com.bayzat.exercise.employee.Employee
import com.bayzat.exercise.employee.EmployeeDto
import com.bayzat.exercise.employee.EmployeeService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(InternalDependantConfig::class))
@DataJpaTest
class JpaDependantServiceTest {

    @Autowired
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var dependantService: DependantService

    @Autowired
    lateinit var companyRepository: CompanyRepository

    /*
	 * Before every test is important we create a Company and Employee so we can add
	 * Dependant
	 */
    @Before
    fun setUp() {
        val createNewDependant = companyService.addCompany(
                CreateCompanyDto(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE")))

        val createNewEmployee = CreateEmployeeDto(
                employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                salary = 1500.00,
                company = Company.fromDto(createNewDependant)
        )

        val result = employeeService.addEmployee(createNewEmployee)
        assertThat(result.employeeId).isNotNull()
        assertThat(result.employeeName).isEqualTo("John")
    }

    /**
     * should return created entity of dependant
     */

    @Test
    fun addDependant() {
        //get firt company and employee in the repository
        val existingCompanies = companyRepository.findAll()
        assertThat(existingCompanies).hasSize(1)
        val existingEmployees = employeeService.retrieveEmployees()
        assertThat(existingEmployees.size).isEqualTo(1)

        val dependant1 = CreateDependantDto(
                dependantName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                relation = Relation.FATHER,
                employee = Employee.fromDto(existingEmployees.first())
        )

        val result = dependantService.addDependant(dependant1)

        assertThat(result.dependantId).isNotNull()
        assertThat(result.dependantName).isEqualTo("John")
        assertThat(result.gender).isEqualTo(Gender.MALE)
    }

    /**
     * should retrieve empty list if repository doesn't contain entities
     */
    @Test
    fun retrieveEmptyDependants() {
        assertThat(dependantService.retrieveDependants()).isEmpty()
    }

    /**
     * should return null if employee  for dependantId doesn't exist
     */
    @Test
    fun retrieveNonExistDependant() {
        assertThat(dependantService.retrieveDependant(-99)).isNull()
    }

    /**
     * should map existing company from repository
     */
    @Test
    fun retrieveExisDependant() {
        //get firt company and employee in the repository
        val existingCompanies = companyRepository.findAll()
        assertThat(existingCompanies).hasSize(1)
        val existingEmployees = employeeService.retrieveEmployees()
        assertThat(existingEmployees.size).isEqualTo(1)

        val dependant1 = CreateDependantDto(
                dependantName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                relation = Relation.FATHER,
                employee = Employee.fromDto(existingEmployees.first())
        )

        val createdDependant = dependantService.addDependant(dependant1)
        val result = dependantService.retrieveDependant(createdDependant.dependantId)

        assertThat(result).isNotNull
        assertThat(result!!.dependantId).isNotNull()
        assertThat(result?.dependantName).isEqualTo("John")
        assertThat(result.gender).isEqualTo(Gender.MALE)
    }


    /**
     * should delete existing company
     */

    @Test
    fun deleteDependant() {
        //get firt company and employee in the repository
        val existingCompanies = companyRepository.findAll()
        assertThat(existingCompanies).hasSize(1)
        val existingEmployees = employeeService.retrieveEmployees()
        assertThat(existingEmployees.size).isEqualTo(1)

        val dependant1 = CreateDependantDto(
                dependantName = "John",
                dateOfBirth = LocalDate.of(1990, 6, 6),
                gender = Gender.MALE,
                phoneNumber = "+971555960195",
                relation = Relation.FATHER,
                employee = Employee.fromDto(existingEmployees.first())
        )

        val createdDependant = dependantService.addDependant(dependant1)
        val deletedDependant = dependantService.retrieveDependant(createdDependant.dependantId)

        assertThat(deletedDependant).isNotNull
        val result= dependantService.deleteDependant(deletedDependant!!.dependantId)
        assertThat(result).isEqualTo(1)
        assertThat(dependantService.retrieveDependant(createdDependant.dependantId)).isNull()

    }
}

