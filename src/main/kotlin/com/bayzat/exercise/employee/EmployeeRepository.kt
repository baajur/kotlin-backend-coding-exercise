package com.bayzat.exercise.employee

import com.bayzat.exercise.company.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun deleteByEmployeeId(employeeId: Long): Long?
}