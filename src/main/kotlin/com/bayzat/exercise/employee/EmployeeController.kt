package com.bayzat.exercise.employee

import com.bayzat.exercise.constant.EMPLOYEES_PATH
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.logging.Level
import java.util.logging.Logger

@RestController
@RequestMapping(value = EMPLOYEES_PATH)
class EmployeeController(val employeeService: EmployeeService) {

    private val log = Logger.getLogger(EmployeeController::class.java.getName())

    @GetMapping
    fun retrieveEmployees(): ResponseEntity<List<EmployeeDto>> {
        log.info("Retrieving employees")
        val result = employeeService.retrieveEmployees()
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("{employeeId}")
    fun retrieveEmployee(@PathVariable("employeeId") employeeId: Long): ResponseEntity<EmployeeDto> {
        log.log(Level.INFO, "Retrieving employee: {}", employeeId)

        val result = employeeService.retrieveEmployee(employeeId)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun addEmployee(@RequestBody employee: CreateEmployeeDto, uriBuilder: UriComponentsBuilder): ResponseEntity<EmployeeDto> {
        log.info("Request to add a employee")

        val result = employeeService.addEmployee(employee)
        return ResponseEntity
                .created(uriBuilder.path("${EMPLOYEES_PATH}/{employeeId}").buildAndExpand(result.employeeId).toUri())
                .body(result)
    }

    @PutMapping("{employeeId}")
    fun updateEmployee(@PathVariable("employeeId") employeeId: Long, @RequestBody employee: UpdateEmployeeDto): ResponseEntity<EmployeeDto>  {
        log.log(Level.INFO, "Request to update employee: {}", employeeId)

        val result = employeeService.updateEmployee(employeeId, employee)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("{employeeId}")
    fun deleteEmployeeById(@PathVariable(value = "employeeId") employeeId: Long): ResponseEntity<Void> {
        val result= employeeService.deleteEmployee(employeeId)
        if ( result != null) {
            log.info("EmployeeId:" + employeeId)
            return ResponseEntity<Void>(HttpStatus.OK)
        }
       else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

}