package com.bayzat.exercise.employee


import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.whenever
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.times
import com.bayzat.exercise.company.Address
import com.bayzat.exercise.company.Company
import com.bayzat.exercise.constant.Gender
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import org.mockito.MockitoAnnotations
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner::class)
@WebMvcTest(EmployeeController::class)
class EmployeeControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    @MockBean
    lateinit var employeeService: EmployeeService
    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
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
                dateOfBirth = LocalDate.of(1990, 8, 1),
                gender = Gender.MALE, company = Company(companyId = 1, companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))


        whenever(employeeService.retrieveEmployee(1)).thenReturn(result)

        this.mockMvc.perform(get("/api/v1/employees/{employeeId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.employeeId", equalTo(1)))
                .andExpect(jsonPath("$.employeeName", equalTo("John")))
                .andExpect(jsonPath("$.gender", equalTo("MALE")))
        verify(employeeService).retrieveEmployee(1)
        verify(employeeService, times(1)).retrieveEmployee(1);

    }

    @Test
    fun `Successfully updating a employee should result in status 200`() {
        val result: EmployeeDto? = EmployeeDto(employeeId = 33, employeeName = "John",
                dateOfBirth = LocalDate.of(1990, 8, 1),
                gender = Gender.MALE, company = Company(companyId = 1, companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))

        val updateEmployee = UpdateEmployeeDto(employeeName = "John", phoneNumber = "+9715255554",
                dateOfBirth = LocalDate.of(1990, 8, 1), salary = 15.0,
                gender = Gender.MALE, company = Company(companyId = 1, companyName = "Bayzat",
                address = Address(city = "Dubai", country = "UAE")))


        whenever(employeeService.updateEmployee(any(), any())).thenReturn(result)

        this.mockMvc.perform(put("/api/v1/employees/{employeeId}", 33)
                .content(objectMapper.writeValueAsString(updateEmployee))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.employeeId", equalTo(33)))
                .andExpect(jsonPath("$.employeeName", equalTo("John")))

        verify(employeeService).updateEmployee(eq(33), any())
        verify(employeeService, times(1)).updateEmployee(eq(33), any());

    }

    @Test
    fun `Successfully deleting a employee should result in status 200`() {
        whenever(employeeService.deleteEmployee(any())).thenReturn(1)
        this.mockMvc.perform(delete("/api/v1/employees/{employeeId}", 33)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)

        verify(employeeService).deleteEmployee(eq(33))
        verify(employeeService, times(1)).deleteEmployee(eq(33));
    }


}