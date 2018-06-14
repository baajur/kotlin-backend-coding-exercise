package com.bayzat.exercise.company

import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(InternalCompanyConfig::class))
@DataJpaTest
class JpaCompanyServiceTest {

    @Autowired
    lateinit var service: CompanyService
    @Autowired
    lateinit var repository: CompanyRepository

    /**
     * should return created entity
     */
    @Test
    fun addCompany() {

        val result = service.addCompany(
                CreateCompanyDto(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE")))

        assertThat(result.companyId).isNotNull()
        assertThat(result.companyName).isEqualTo("Bayzat")
        assertThat(result.employees).isEmpty()
    }

    /**
     * should retrieve empty list if repository doesn't contain entities
     */
    @Test
    fun retrieveCompanies() {
        assertThat(service.retrieveCompanies()).isEmpty()
    }


    fun `'updateCity' should update existing values`() {
        val existingCompany = repository.save(Company(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE"))).toDto()

        val result = service.updateCompany(existingCompany.companyId,
                UpdateCompanyDto(companyName = "company1",
                        address = Address(city = "city1", country = "country1")))
        assertThat(result).isNotNull
        assertThat(result?.companyId).isEqualTo(existingCompany.companyId)
        assertThat(result?.companyName).isEqualTo("company1")
        assertThat(result?.address?.city).isEqualTo("city1")
        assertThat(result?.address?.country).isEqualTo("country1")
    }




    @Test
    fun `'deleteCompany' should delete existing company`() {
        val newCompany = repository.save(Company(companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE"))).toDto()
        val existingCompany= repository.findAll().stream().findFirst().get().toDto()
        assertThat(existingCompany).isNotNull
        service.deleteCompany(existingCompany.companyId)
    }
}