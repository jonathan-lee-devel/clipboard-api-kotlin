package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.model.RegistrationVerificationToken

interface RegistrationVerificationTokenService {

    fun generateAndPersistRegistrationVerificationToken(): RegistrationVerificationToken
    fun findTokenByValue(tokenValue: String): RegistrationVerificationToken?
    fun expireToken(registrationVerificationToken: RegistrationVerificationToken)

}
