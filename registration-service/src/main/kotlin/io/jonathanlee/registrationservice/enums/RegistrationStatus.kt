package io.jonathanlee.registrationservice.enums

enum class RegistrationStatus {
    SUCCESS,
    PASSWORDS_DO_NOT_MATCH,
    AWAITING_EMAIL_VERIFICATION,
    FAILURE,
    INVALID_TOKEN,
    EMAIL_VERIFICATION_EXPIRED,
}
