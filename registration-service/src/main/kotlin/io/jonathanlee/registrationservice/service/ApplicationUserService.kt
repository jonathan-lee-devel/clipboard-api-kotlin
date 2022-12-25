package io.jonathanlee.registrationservice.service

import io.jonathanlee.registrationservice.model.ApplicationUser

interface ApplicationUserService {

    fun persistApplicationUser(applicationUser: ApplicationUser): ApplicationUser

}
