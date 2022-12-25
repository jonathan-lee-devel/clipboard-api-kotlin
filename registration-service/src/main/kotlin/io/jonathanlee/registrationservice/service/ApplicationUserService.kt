package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.model.RegistrationVerificationToken

interface ApplicationUserService {

    fun persistApplicationUser(applicationUser: ApplicationUser): ApplicationUser
    fun findByRegistrationVerificationToken(registrationVerificationToken: RegistrationVerificationToken): ApplicationUser
    fun enableUser(user: ApplicationUser): ApplicationUser

}
