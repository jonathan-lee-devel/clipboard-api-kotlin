package io.jonathanlee.registrationservice.error

import io.jonathanlee.registrationservice.dto.Dto
import jakarta.validation.Validation
import jakarta.validation.Validator

class ValidationHelper {

    companion object {
        
        private val validator: Validator = Validation.buildDefaultValidatorFactory().validator

        fun <T : Dto> validate(dto: T): Collection<ValidationErrorDto> {
            val constraintViolations = this.validator.validate(dto)
            val errors = ArrayList<ValidationErrorDto>()
            if (constraintViolations.isNotEmpty()) {
                constraintViolations.forEach { constraintViolation ->
                    errors.add(
                        ValidationErrorDto(
                            constraintViolation.propertyPath.toString(),
                            constraintViolation.message
                        )
                    )
                }
            }

            return errors
        }
    }

}
