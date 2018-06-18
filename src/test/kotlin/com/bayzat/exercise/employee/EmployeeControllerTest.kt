package com.bayzat.exercise.employee

import com.bayzat.exercise.company.Address
import com.bayzat.exercise.company.Company
import com.bayzat.exercise.constant.Gender
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@WebMvcTest(EmployeeController::class)
class EmployeeControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var employeeService: EmployeeService

    @TestConfiguration
    class Config {

        @Bean
        fun employeeService(): EmployeeService = Mockito.mock(EmployeeService::class.java)
    }

    @Before
    fun setup() {
        reset(employeeService)
    }

    @Test
    fun `Retrieving employee with string,  should result in status 400`() {
        mockMvc.perform(get("/api/v1/employees/unknown")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `Creating a employee with an invalid request body should result in status 400 `() {
        mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `Retrieving an a known employee should result in status 200`() {
        val result: EmployeeDto? = EmployeeDto(employeeId = 1, employeeName = "John",
                dateOfBirth =  LocalDate.of(1990, 8, 1),
                gender = Gender.MALE, company = Company (companyId = 1, companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))


        whenever(employeeService.retrieveEmployee(1)).thenReturn(
                result)

        this.mockMvc.perform(get("/api/v1/employees/{employeeId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.employeeId", equalTo(1)))
                .andExpect(jsonPath("$.employeeName", equalTo("John")))
                .andExpect(jsonPath("$.gender", equalTo("MALE")))
    }





}