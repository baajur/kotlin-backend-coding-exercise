package com.bayzat.exercise.dependant

import com.bayzat.exercise.constant.DEPENDANTS_PATH
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.logging.Level
import java.util.logging.Logger

@RestController
@RequestMapping(value = DEPENDANTS_PATH)
class DependantController(val dependantService: DependantService) {

    private val log = Logger.getLogger(DependantController::class.java.getName())

    @GetMapping
    fun retrieveDependants(): ResponseEntity<List<DependantDto>> {
        log.info("Retrieving dependant")
        val result = dependantService.retrieveDependants()
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("{dependantId}")
    fun retrieveDependant(@PathVariable("dependantId") dependantId: Long): ResponseEntity<DependantDto> {
        log.log(Level.INFO, "Retrieving dependant: {}", dependantId)

        val result = dependantService.retrieveDependant(dependantId)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun addDependant(@RequestBody dependant: CreateDependantDto, uriBuilder: UriComponentsBuilder): ResponseEntity<DependantDto> {
        log.info("Request to add a dependant")

        val result = dependantService.addDependant(dependant)
        return ResponseEntity
                .created(uriBuilder.path("${DEPENDANTS_PATH}/{dependantId}").buildAndExpand(result.dependantId).toUri())
                .body(result)
    }

    @PutMapping("{dependantId}")
    fun updateDependant(@PathVariable("dependantId") dependantId: Long, @RequestBody dependant: UpdateDependantDto): ResponseEntity<DependantDto>  {
        log.log(Level.INFO, "Request to update dependant: {}", dependantId)

        val result = dependantService.updateDependant(dependantId, dependant)
        if (result != null) {
            return ResponseEntity.ok(result)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("{dependantId}")
    fun deleteDependantById(@PathVariable(value = "dependantId") dependantId: Long): ResponseEntity<Void> {
        val result= dependantService.deleteDependant(dependantId)
        if ( result != null) {
            return ResponseEntity<Void>(HttpStatus.OK)
        }
       else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

}