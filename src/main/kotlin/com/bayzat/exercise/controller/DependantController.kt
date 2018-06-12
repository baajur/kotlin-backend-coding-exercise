package com.bayzat.exercise.controller

import com.bayzat.exercise.domain.Dependant
import com.bayzat.exercise.repository.DependantRepository
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.logging.Level
import java.util.logging.Logger
import javax.transaction.TransactionScoped
import javax.validation.Valid

@RestController
class DependantController {
    private val LOGGER = Logger.getLogger(DependantController::class.qualifiedName)

    @Autowired
    lateinit var dependantRepository: DependantRepository

    @GetMapping("/dependants")
    fun getAllDependants(): List<Dependant> =
            dependantRepository.findAll()

    //Get all the dependants without their employee
    @GetMapping("/dependants/{dependantId}")
    fun getDependantById(@PathVariable(value = "dependantId") dependantId: Long): ResponseEntity<Dependant> {
        return dependantRepository.findById(dependantId).map { dependant ->
            ResponseEntity.ok(dependant)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/dependants")
    fun createNewDependant(@Valid @RequestBody dependant: Dependant): Dependant =
            dependantRepository.save(dependant)



    @PutMapping("/dependants/{dependantId}")
    fun updateDependantById(@PathVariable(value = "dependantId") dependantId: Long,
                           @Valid @RequestBody newDependant: Dependant): ResponseEntity<Dependant> {

        return dependantRepository.findById(dependantId).map { existingDependant ->
            val updatedDependant: Dependant = existingDependant
                    .copy(
                            dependantName = newDependant.dependantName,
                            phoneNumber = newDependant.phoneNumber,
                            gender =  newDependant.gender,
                            dateOfBirth = newDependant.dateOfBirth,
                            relation = newDependant.relation,
                            employee = newDependant.employee
                    )
            ResponseEntity.ok().body(dependantRepository.save(updatedDependant))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/dependants/{dependantId}")
    @TransactionScoped
    fun deleteDependantById(@PathVariable(value = "dependantId") dependantId: Long): ResponseEntity<Void> {
        return dependantRepository.findById(dependantId).map { dependant ->
            dependantRepository.delete(dependant)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }

}