package io.jonathanlee.registrationservice.repository

import io.jonathanlee.registrationservice.model.RegistrationVerificationToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RegistrationVerificationTokenRepository :
    MongoRepository<RegistrationVerificationToken, ObjectId> {

    fun findByValue(value: String): RegistrationVerificationToken?

}