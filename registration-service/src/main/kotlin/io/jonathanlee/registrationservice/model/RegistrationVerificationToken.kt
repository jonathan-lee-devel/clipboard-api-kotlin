package io.jonathanlee.registrationservice.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

@Document
data class RegistrationVerificationToken(
    @field:Id @field:Field("_id") val objectId: ObjectId,
    val id: String,
    val value: String,
    var expiryDate: Instant,
)
