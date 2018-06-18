package com.bayzat.exercise.employee

interface EmployeeService {
    fun retrieveEmployee(employeeId: Long): EmployeeDto?

    fun retrieveEmployees(): List<EmployeeDto>

    fun addEmployee(employee: CreateEmployeeDto): EmployeeDto

    /**
     * Only existing employee will be updated, if the employee does'not exist
     * then don't update and return null ?
     * @param employeeId will be used to find whether employee exist or not
     * @param UpdateEmployeeDto it's the data the client sent and needs updating
     * @return updated Employee data Only existing employee will be updated and return
     */
    fun updateEmployee(employeeId: Long, employee: UpdateEmployeeDto): EmployeeDto?

    fun deleteEmployee(employeeId: Long): Long?
}