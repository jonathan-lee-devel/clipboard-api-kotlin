package io.jonathanlee.registrationservice.dto

import io.jonathanlee.registrationservice.enums.RegistrationStatus

data class RegistrationStatusDto(
    val registrationStatus: RegistrationStatus,
) : Dto()
