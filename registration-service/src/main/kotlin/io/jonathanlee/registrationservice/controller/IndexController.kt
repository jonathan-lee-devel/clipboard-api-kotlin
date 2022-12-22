package io.jonathanlee.registrationservice.controller

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.model.PasswordResetToken
import io.jonathanlee.registrationservice.model.RegistrationVerificationToken
import io.jonathanlee.registrationservice.repository.ApplicationUserRepository
import io.jonathanlee.registrationservice.repository.PasswordResetTokenRepository
import io.jonathanlee.registrationservice.repository.RegistrationVerificationTokenRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.Instant
import javax.validation.Valid

@RestController
@RequestMapping("/")
class IndexController(
    private val passwordEncoder: PasswordEncoder,
    private val registrationVerificationTokenRepository: RegistrationVerificationTokenRepository,
    private val passwordResetTokenRepository: PasswordResetTokenRepository,
    private val applicationUserRepository: ApplicationUserRepository
) {

    @GetMapping
    fun getIndex(): ResponseEntity<Instant> {
        return ResponseEntity.status(HttpStatus.OK).body(Instant.now())
    }

    @PostMapping("/validation")
    fun testValidation(
        @Valid @RequestBody registrationDto: RegistrationDto
    ): ResponseEntity<RegistrationDto> {
        return ResponseEntity.status(HttpStatus.OK).body(registrationDto)
    }

    @GetMapping("/demo", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun demoUser(): ResponseEntity<ApplicationUser> {
        val user = ApplicationUser(
            ObjectId.get(),
            "123",
            "jonathan.lee.devel@gmail.com",
            this.passwordEncoder.encode("password"),
            "firstName",
            "lastName",
            false,
            this.registrationVerificationTokenRepository.save(
                RegistrationVerificationToken(
                    ObjectId.get(),
                    "123",
                    "value",
                    Instant.now()
                )
            ),
            this.passwordResetTokenRepository.save(
                PasswordResetToken(
                    ObjectId.get(),
                    "123",
                    "value",
                    Instant.now()
                )
            )
        )
        this.applicationUserRepository.save(user)

        return ResponseEntity.status(HttpStatus.OK).body(
            this.applicationUserRepository.findByEmail("jonathan.lee.devel@gmail.com")
        )
    }

}
