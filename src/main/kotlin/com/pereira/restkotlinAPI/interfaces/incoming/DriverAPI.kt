package com.pereira.restkotlinAPI.interfaces.incoming

import com.pereira.restkotlinAPI.domain.Driver
import com.pereira.restkotlinAPI.domain.DriverRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate


@Service
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DriverAPI(val driverRepository: DriverRepository) {

    //* O método findAll() retorna uma lista de Drivers buscado no DriverRepository.
    @GetMapping("/drivers")
    fun listDrivers() = driverRepository.findAll()

    @GetMapping("/drivers/{id}")
    fun findDriver(@PathVariable id: Long) = driverRepository
        .findById(id)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping("/drivers")
    fun createDriver(@RequestBody driver: Driver) = driverRepository.save(driver)

    @PutMapping("/drivers/{id}")
    fun fullUpdateDriver(@PathVariable id: Long, @RequestBody driver: Driver): Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            name = driver.name,
            birthDate = driver.birthDate
        )
        return driverRepository.save(copyDriver)
    }

    @PatchMapping("/drivers/{id}")
    fun incrementalUpdateDriver(@PathVariable("id") id:Long, @RequestBody driver: PatchDriver
    ) : Driver {
        val foundDriver = findDriver(id)
        val copyDriver = foundDriver.copy(
            birthDate = driver.birthDate ?: foundDriver.birthDate,
            name = driver.name ?: foundDriver.name
        )
        return driverRepository.save(copyDriver)
    }

    @DeleteMapping("/drivers/{id}")
    fun deleteDriver(@PathVariable id: Long) = driverRepository.delete(findDriver(id))
}

data class PatchDriver(
    var name: String? = null,
    var birthDate: LocalDate? = null
)
