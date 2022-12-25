package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.model.PasswordResetToken

interface PasswordResetTokenService {

    fun generateAndPersistNewExpiredPasswordResetToken(): PasswordResetToken

    fun generateAndPersistNewPasswordResetToken(): PasswordResetToken

}
