package com.bayzat.exercise.employee

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class JpaEmployeeService(val employeeRepo: EmployeeRepository) : EmployeeService {

    override fun retrieveEmployee(employeeId: Long): EmployeeDto? {
        return employeeRepo.findById(employeeId).map { existingEmployee ->
            existingEmployee.toDto()
        }.orElse(null)
    }

    override fun retrieveEmployees(): List<EmployeeDto> {
        return employeeRepo.findAll().map { it.toDto() }
    }

    override fun addEmployee(employee: CreateEmployeeDto): EmployeeDto {
        return employeeRepo.save(Employee.fromDto(employee)).toDto()
    }

    override fun updateEmployee(employeeId: Long, employee: UpdateEmployeeDto): EmployeeDto? {
        return employeeRepo.findById(employeeId).map { currentEmployee ->
            employeeRepo.save(Employee.fromDto(employee, currentEmployee)).toDto()
        }.orElse(null)
    }

    override fun deleteEmployee(employeeId: Long): Long? {
        return employeeRepo.findById(employeeId).map { currentEmployee ->
            employeeRepo.deleteByEmployeeId(employeeId)
        }.orElse(null)
    }
}