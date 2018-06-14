package com.bayzat.exercise.dependant

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class JpaDependantService(val dependantRepository: DependantRepository) : DependantService {

    override fun retrieveDependant(dependantId: Long): DependantDto? {
        return dependantRepository.findById(dependantId).map { existingDependant ->
            existingDependant.toDto()
        }.orElse(null)
    }

    override fun retrieveDependants(): List<DependantDto> {
        return dependantRepository.findAll().map { it.toDto() }
    }

    override fun addDependant(dependant: CreateDependantDto): DependantDto {
        return dependantRepository.save(Dependant.fromDto(dependant)).toDto()
    }

    override fun updateDependant(dependantId: Long, dependant: UpdateDependantDto): DependantDto? {
        return dependantRepository.findById(dependantId).map { currentDependant ->
            dependantRepository.save(Dependant.fromDto(dependant, currentDependant)).toDto()
        }.orElse(null)
    }


    override fun deleteDependant(dependantId: Long): Long? {
        return dependantRepository.findById(dependantId).map { currentDependant ->
            dependantRepository.deleteByDependantId(dependantId)
        }.orElse(null)
    }
}