package com.bayzat.exercise.repository

import com.bayzat.exercise.domain.Dependant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DependantRepository : JpaRepository<Dependant, Long>