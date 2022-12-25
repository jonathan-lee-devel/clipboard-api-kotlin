package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.model.RegistrationVerificationToken

interface RegistrationVerificationTokenService {

    fun generateAndPersistRegistrationVerificationToken(): RegistrationVerificationToken

}
