package io.jonathanlee.registrationservice.dto

import io.jonathanlee.registrationservice.validation.Constraints
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RegistrationDto(
    @field:NotNull @field:Email val email: String,
    @field:NotNull @field:Size(
        min = Constraints.MIN_NAME_LENGTH,
        max = Constraints.MAX_NAME_LENGTH
    ) val firstName: String,
    @field:NotNull val lastName: String,
    @field:NotNull @field:Size(
        min = Constraints.MIN_PASSWORD_LENGTH,
        max = Constraints.MAX_PASSWORD_LENGTH
    ) val password: String,
    @field:NotNull @field:Size(
        min = Constraints.MIN_PASSWORD_LENGTH,
        max = Constraints.MAX_PASSWORD_LENGTH
    ) val confirmPassword: String,
) : Dto()
