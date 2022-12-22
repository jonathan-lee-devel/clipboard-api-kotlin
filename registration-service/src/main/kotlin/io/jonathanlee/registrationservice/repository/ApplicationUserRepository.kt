package io.jonathanlee.registrationservice.repository

import io.jonathanlee.registrationservice.model.ApplicationUser
import io.jonathanlee.registrationservice.model.PasswordResetToken
import io.jonathanlee.registrationservice.model.RegistrationVerificationToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ApplicationUserRepository : MongoRepository<ApplicationUser, ObjectId> {

    fun findByEmail(email: String): ApplicationUser

    fun findByRegistrationVerificationToken(registrationVerificationToken: RegistrationVerificationToken): ApplicationUser

    fun findByPasswordResetToken(passwordResetToken: PasswordResetToken): ApplicationUser

}