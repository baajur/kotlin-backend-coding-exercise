package com.bayzat.exercise.dependant

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DependantRepository : JpaRepository<Dependant, Long> {
    fun deleteByDependantId(dependantId: Long): Long?
}