package io.jonathanlee.clipboardapi.model.customer

import javax.persistence.Entity
import javax.persistence.Id

/**
 * Entity used to represent a customer.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class CustomerModel(
    @Id val id: Long,
    val customerId: String,
)
