package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.repository.ApplicationUserRepository
import io.jonathanlee.registrationservice.service.ApplicationUserService
import org.springframework.stereotype.Service

@Service
class ApplicationUserServiceImpl(
    private val applicationUserRepository: ApplicationUserRepository,
) : ApplicationUserService {

    override fun persistApplicationUser(applicationUser: ApplicationUser): ApplicationUser {
        return this.applicationUserRepository.save(applicationUser)
    }

}
