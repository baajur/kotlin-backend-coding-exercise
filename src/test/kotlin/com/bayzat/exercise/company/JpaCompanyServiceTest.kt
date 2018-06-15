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

    /**
     * should update existing value
     */
    @Test
    fun updateCompany() {
        val createdCompany = service.addCompany(CreateCompanyDto(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE")))

        val result = service.updateCompany(createdCompany.companyId,
                UpdateCompanyDto(companyName = "company1",
                        address = Address(city = "city1", country = "country1")))
        assertThat(result).isNotNull
        assertThat(result?.companyId).isEqualTo(createdCompany.companyId)
        assertThat(result?.companyName).isEqualTo("company1")
        assertThat(result?.address?.city).isEqualTo("city1")
        assertThat(result?.address?.country).isEqualTo("country1")
    }

    /**
     * should return null if company for companyId doesn't exist
     */
    @Test
    fun retrieveNullCompany() {
        assertThat(service.retrieveCompany(-99)).isNull()
    }

    /**
     * should map existing company from repository
     */
    @Test
    fun retrieveExistingCompany() {
        val createdCompany = service.addCompany(CreateCompanyDto(companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))
        val result= service.retrieveCompany(createdCompany.companyId)


        assertThat(result).isNotNull
        assertThat(result!!.companyId).isPositive()
        assertThat(result?.companyName).isEqualTo("Bayzat")
        assertThat(result.address?.city).isEqualTo("Dubai")
    }

    /**
     * should delete existing company`
     */
    @Test
    fun deleteCompany() {
        val createdCompany = service.addCompany(CreateCompanyDto(companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))
        val existingCompany= service.retrieveCompany(createdCompany.companyId)
        assertThat(existingCompany).isNotNull
        val result= service.deleteCompany(existingCompany!!.companyId)
        assertThat(result).isEqualTo(1)
        assertThat(service.retrieveCompany(existingCompany.companyId)).isNull()

    }
}