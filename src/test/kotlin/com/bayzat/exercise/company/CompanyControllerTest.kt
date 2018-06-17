package com.bayzat.exercise.company

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(CompanyController::class)
@ContextConfiguration(classes = arrayOf(CompanyService::class))
class CompanyControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var companyService: CompanyService




    @TestConfiguration
    class Config {

        @Bean
        fun companyService(): CompanyService = Mockito.mock(CompanyService::class.java)
    }

    /**Retrieving an unknown company should result in status 404
     *
     */
    @Test
    fun `Retrieving an unknown company should result in status 400`() {
        mockMvc.perform(get("/api/v1/companies/unkown")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `Creating a company with an invalid request body should result in status 400 `() {
        mockMvc.perform(post("/api/v1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isNotFound)
    }


    //fun `Creating a company with a valid request body should result in status 201 and a location header`() {
    @Test
    fun createCompany() {
        val saveNewCompany =
                CreateCompanyDto(companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE"))



        `when`(companyService.addCompany(any())).thenReturn(
                CompanyDto(companyId = 1, companyName = "Bayzat",
                        address = Address(city = "Dubai", country = "UAE")))

        mockMvc.perform(post("/api/v1/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(saveNewCompany)))
                .andExpect(status().isNotFound)
        // .andExpect(header().string("location", "http://localhost/companies/1"))
        verify(companyService, times(1)).addCompany(any())
        verify(companyService).addCompany(any())

    }


    /**Retrieving an unknown company should result in status 404
     *
     */
    @Test
    fun `Retrieving an a known company should result in status 200`() {
        val result: CompanyDto? = CompanyDto(companyId = 1, companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE"))

        `when`(companyService.retrieveCompany(1)).thenReturn(
                result)

        this.mockMvc.perform(get("/api/v1/companies/{companyId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }


}