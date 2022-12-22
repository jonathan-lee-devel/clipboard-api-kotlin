package io.jonathanlee.registrationservice.error

data class ValidationErrorDto(
    val field: String,
    val message: String,
)
