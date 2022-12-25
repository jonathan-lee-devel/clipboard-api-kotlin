package io.jonathanlee.registrationservice.controller

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.enums.RegistrationStatus
import io.jonathanlee.registrationservice.error.ValidationHelper
import io.jonathanlee.registrationservice.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/register")
class RegistrationController(
    private val registrationService: RegistrationService,
) {

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

        val registrationStatusDto = this.registrationService.registerNewUser(registrationDto)

        return when(registrationStatusDto.registrationStatus) {
            RegistrationStatus.AWAITING_EMAIL_VERIFICATION -> ResponseEntity.status(HttpStatus.OK).body(registrationStatusDto)
            RegistrationStatus.PASSWORDS_DO_NOT_MATCH -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationStatusDto)
            RegistrationStatus.SUCCESS, RegistrationStatus.FAILURE -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registrationStatusDto)
        }
    }

}
