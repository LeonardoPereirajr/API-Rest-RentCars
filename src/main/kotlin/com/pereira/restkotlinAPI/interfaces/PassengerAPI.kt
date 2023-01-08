package com.pereira.restkotlinAPI.interfaces

import com.pereira.restkotlinAPI.domain.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Service
@RestController
@RequestMapping(path = ["/passengers"],produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI(val passengerRepository: PassengerRepository) {

    //* O método findAll() retorna uma lista de Drivers buscado no DriverRepository.
    @GetMapping()
    fun listPassengers() = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassengers(@PathVariable id: Long) = passengerRepository
        .findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping()
    fun createPassengers(@RequestBody passenger: Passenger) = passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun fullUpdatePassengers(@PathVariable id: Long, @RequestBody passenger: Passenger): Passenger {
        val foundPassenger = findPassengers(id)
        val copyPassenger = foundPassenger.copy(
            name = passenger.name,
        )
        return passengerRepository.save(copyPassenger)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdatePassengers(@PathVariable("id") id:Long, @RequestBody passenger: PatchDriver
    ) : Passenger {
        val foundDriver = findPassengers(id)
        val copyPassenger = foundDriver.copy(
            name = passenger.name ?: foundDriver.name
        )
        return passengerRepository.save(copyPassenger)
    }

    @DeleteMapping("/{id}")
    fun deletePassengers(@PathVariable id: Long) = passengerRepository.delete(findPassengers(id))
}
