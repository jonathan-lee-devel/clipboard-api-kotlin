package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.dto.RegistrationStatusDto
import io.jonathanlee.registrationservice.enums.RegistrationStatus
import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.service.*
import org.bson.types.ObjectId
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegistrationServiceImpl(
    private val randomService: RandomService,
    private val passwordEncoder: PasswordEncoder,
    private val registrationVerificationTokenService: RegistrationVerificationTokenService,
    private val passwordResetTokenService: PasswordResetTokenService,
    private val applicationUserService: ApplicationUserService,
) : RegistrationService {

    override fun registerNewUser(registrationDto: RegistrationDto): RegistrationStatusDto {
        if (registrationDto.password != registrationDto.confirmPassword) {
            return RegistrationStatusDto(RegistrationStatus.PASSWORDS_DO_NOT_MATCH)
        }

        val newApplicationUser = ApplicationUser(
            ObjectId.get(),
            this.randomService.generateNewId(),
            registrationDto.email,
            this.passwordEncoder.encode(registrationDto.password),
            registrationDto.firstName,
            registrationDto.lastName,
            false,
            this.registrationVerificationTokenService.generateAndPersistRegistrationVerificationToken(),
            this.passwordResetTokenService.generateAndPersistNewExpiredPasswordResetToken(),
        )

        val savedApplicationUser = this.applicationUserService.persistApplicationUser(newApplicationUser)
        if (newApplicationUser.id == savedApplicationUser.id) {// If save was successful
            return RegistrationStatusDto(RegistrationStatus.AWAITING_EMAIL_VERIFICATION)
        }

        return RegistrationStatusDto(RegistrationStatus.FAILURE)
    }

}
