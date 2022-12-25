package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.model.PasswordResetToken
import io.jonathanlee.registrationservice.repository.PasswordResetTokenRepository
import io.jonathanlee.registrationservice.service.PasswordResetTokenService
import io.jonathanlee.registrationservice.service.RandomService
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PasswordResetTokenServiceImpl(
    private val passwordResetTokenRepository: PasswordResetTokenRepository,
    private val randomService: RandomService,
) : PasswordResetTokenService {

    companion object {
        const val TOKEN_EXPIRY_TIME_MINUTES = 15L
    }

    override fun generateAndPersistNewExpiredPasswordResetToken(): PasswordResetToken {
        return this.passwordResetTokenRepository.save(
            PasswordResetToken(
                ObjectId.get(),
                this.randomService.generateNewId(),
                this.randomService.generateNewTokenValue(),
                ZonedDateTime.now().toInstant()
            )
        )
    }

    override fun generateAndPersistNewPasswordResetToken(): PasswordResetToken {
        return this.passwordResetTokenRepository.save(
            PasswordResetToken(
                ObjectId.get(),
                this.randomService.generateNewId(),
                this.randomService.generateNewTokenValue(),
                ZonedDateTime.now().plusMinutes(TOKEN_EXPIRY_TIME_MINUTES).toInstant()
            )
        )
    }

}
