package com.bayzat.exercise.controller


import com.bayzat.exercise.domain.Employee
import com.bayzat.exercise.repository.EmployeeRepository
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.transaction.TransactionScoped
import javax.validation.Valid

@RestController


class EmployeeController(private val employeeRepository: EmployeeRepository) {

    @GetMapping("/employees")
    fun getAllEmployees(): List<Employee> =
            employeeRepository.findAll()

    //Get all the employees without their company and dependant
    @GetMapping("/employees/{employeeId}")
    fun getEmployeeById(@PathVariable(value = "employeeId") employeeId: Long): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { employee ->
            ResponseEntity.ok(employee)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/employees")
    fun createNewEmployee(@Valid @RequestBody employee: Employee): Employee =
            employeeRepository.save(employee)

    @PutMapping("/employees/{employeeId}")
    fun updateEmployeeById(@PathVariable(value = "employeeId") employeeId: Long,
                          @Valid @RequestBody newEmployee: Employee): ResponseEntity<Employee> {

        return employeeRepository.findById(employeeId).map { existingCompany ->
            val updatedEmployee: Employee = existingCompany
                    .copy(
                            employeeName = newEmployee.employeeName,
                            phoneNumber = newEmployee.phoneNumber,
                            gender =  newEmployee.gender,
                            salary = newEmployee.salary,
                            dateOfBirth = newEmployee.dateOfBirth,
                            company = newEmployee.company)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/employees/{employeeId}")
    @TransactionScoped
    fun deleteEmployeeById(@PathVariable(value = "employeeId") employeeId: Long): ResponseEntity<Void> {

        return employeeRepository.findById(employeeId).map { employee ->
            employeeRepository.delete(employee)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

}