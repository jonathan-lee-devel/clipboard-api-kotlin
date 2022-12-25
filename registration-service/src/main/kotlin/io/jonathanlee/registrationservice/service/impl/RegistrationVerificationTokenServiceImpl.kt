package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.model.RegistrationVerificationToken
import io.jonathanlee.registrationservice.repository.RegistrationVerificationTokenRepository
import io.jonathanlee.registrationservice.service.RandomService
import io.jonathanlee.registrationservice.service.RegistrationVerificationTokenService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.ZonedDateTime

@Service
class RegistrationVerificationTokenServiceImpl(
    private val randomService: RandomService,
    private val registrationVerificationTokenRepository: RegistrationVerificationTokenRepository
) : RegistrationVerificationTokenService {

    companion object {
        const val TOKEN_EXPIRY_TIME_MINUTES = 15L
    }

    override fun generateAndPersistRegistrationVerificationToken(): RegistrationVerificationToken {
        return this.registrationVerificationTokenRepository.save(
            RegistrationVerificationToken(
                ObjectId.get(),
                this.randomService.generateNewId(),
                this.randomService.generateNewTokenValue(),
                ZonedDateTime.now().plusMinutes(TOKEN_EXPIRY_TIME_MINUTES).toInstant()
            )
        )
    }

    override fun findTokenByValue(tokenValue: String): RegistrationVerificationToken? {
        return this.registrationVerificationTokenRepository.findByValue(tokenValue)
    }

    override fun expireToken(registrationVerificationToken: RegistrationVerificationToken) {
        registrationVerificationToken.expiryDate = Instant.now()
        this.registrationVerificationTokenRepository.save(registrationVerificationToken)
    }

}
