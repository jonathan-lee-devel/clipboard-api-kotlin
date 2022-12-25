package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.dto.RegistrationDto
import io.jonathanlee.registrationservice.dto.RegistrationStatusDto

interface RegistrationService {

    fun registerNewUser(registrationDto: RegistrationDto): RegistrationStatusDto

}
