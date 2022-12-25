package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.dto.RegistrationStatusDto
import io.jonathanlee.registrationservice.enums.RegistrationStatus
import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.service.*
import org.bson.types.ObjectId
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

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

    override fun confirmNewUserRegistration(tokenValue: String): RegistrationStatusDto {
        val registrationVerificationToken = this.registrationVerificationTokenService.findTokenByValue(tokenValue)
        if (registrationVerificationToken === null) {
            return RegistrationStatusDto(RegistrationStatus.INVALID_TOKEN)
        }

        if (!registrationVerificationToken.expiryDate.isBefore(Instant.now())) {
            val userToEnable = this.applicationUserService.findByRegistrationVerificationToken(registrationVerificationToken)
            val updatedUser = this.applicationUserService.enableUser(userToEnable)
            this.registrationVerificationTokenService.expireToken(registrationVerificationToken)
            if (updatedUser.enabled) {
                return RegistrationStatusDto(RegistrationStatus.SUCCESS)
            }
        } else {
            return RegistrationStatusDto(RegistrationStatus.EMAIL_VERIFICATION_EXPIRED)
        }

        return RegistrationStatusDto(RegistrationStatus.FAILURE)
    }

}
