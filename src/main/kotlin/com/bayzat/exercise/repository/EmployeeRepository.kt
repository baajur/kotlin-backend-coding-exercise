package com.bayzat.exercise.repository

import com.bayzat.exercise.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>{
    fun findByEmployeeName(name: String): Optional<Employee>
}