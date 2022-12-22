package io.jonathanlee.registrationservice.controller

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.error.ValidationHelper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/register")
class RegistrationController {

    @Validated
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun registerNewUser(
        @RequestBody registrationDto: RegistrationDto
    ): ResponseEntity<out Any>? {
        val errors = ValidationHelper.validate(registrationDto)
        if (errors.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registrationDto)
    }

}
