package io.jonathanlee.clipboardapi.model.order

import io.jonathanlee.clipboardapi.model.customer.CustomerModel
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class OrderModel(
    @Id private val id: Long,
    private val orderId: String,
    @ManyToOne private val customer: CustomerModel,
)
