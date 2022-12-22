package io.jonathanlee.registrationservice.error

data class BadRequestDto(
    val errors: List<ValidationErrorDto>
)
