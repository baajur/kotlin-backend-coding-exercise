package com.bayzat.exercise.dependant

interface DependantService {
    fun retrieveDependant(dependantId: Long): DependantDto?

    fun retrieveDependants(): List<DependantDto>

    fun addDependant(dependant: CreateDependantDto): DependantDto

    /**
     * Only existing employee will be updated, if the employee does'not exist
     * then don't update and return null ?
     * @param dependantId will be used to find whether employee exist or not
     * @param UpdateEmployeeDto it's the data the client sent and needs updating
     * @return updated Employee data Only existing employee will be updated and return
     */
    fun updateDependant(dependantId: Long, dependant: UpdateDependantDto): DependantDto?

    fun deleteDependant(dependantId: Long): Long?
}