package io.jonathanlee.clipboardapi.model.customer

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CustomerModel(
    @Id private val id: Long,
    private val customerId: String,
)
