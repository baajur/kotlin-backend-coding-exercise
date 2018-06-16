package com.bayzat.exercise.company

import com.bayzat.exercise.constant.COMPANIES_PATH
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.hamcrest.CoreMatchers.equalTo
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.mockito.BDDMockito.`when`
import org.mockito.Mock
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@WebMvcTest(CompanyController::class)
@ContextConfiguration(classes = arrayOf(CompanyService::class))
class CompanyControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    @MockBean
    lateinit var companyService: CompanyService


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
            /**Retrieving an unknown company should result in status 404
             *
             */
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


}