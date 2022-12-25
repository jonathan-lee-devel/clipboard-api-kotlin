package io.jonathanlee.registrationservice.controller

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.dto.RegistrationStatusDto
import io.jonathanlee.registrationservice.enums.RegistrationStatus
import io.jonathanlee.registrationservice.error.ValidationHelper
import io.jonathanlee.registrationservice.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registrationStatusDto)
        }
    }

    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        value = ["/confirm/{tokenValue}"]
    )
    fun confirmNewUserRegistration(
        @PathVariable tokenValue: String,
    ): ResponseEntity<RegistrationStatusDto> {
        val registrationStatusDto = this.registrationService.confirmNewUserRegistration(tokenValue)

        return when(registrationStatusDto.registrationStatus) {
            RegistrationStatus.SUCCESS -> ResponseEntity.status(HttpStatus.OK).body(registrationStatusDto)
            RegistrationStatus.INVALID_TOKEN, RegistrationStatus.EMAIL_VERIFICATION_EXPIRED -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registrationStatusDto)
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(registrationStatusDto)
        }
    }

}
