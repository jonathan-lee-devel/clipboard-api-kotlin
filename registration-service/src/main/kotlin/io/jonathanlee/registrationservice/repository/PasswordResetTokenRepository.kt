package io.jonathanlee.registrationservice.repository

import io.jonathanlee.registrationservice.model.PasswordResetToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PasswordResetTokenRepository : MongoRepository<PasswordResetToken, ObjectId> {

    fun findByValue(value: String)

}