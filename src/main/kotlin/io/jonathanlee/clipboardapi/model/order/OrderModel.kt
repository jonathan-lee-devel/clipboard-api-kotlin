package io.jonathanlee.clipboardapi.model.order

import io.jonathanlee.clipboardapi.model.customer.CustomerModel
import javax.persistence.*

/**
 * Entity used to represent an order placed by a customer.
 *
 * @author Jonathan Lee <jonthan.lee.devel@gmail.com>
 */
@Entity
data class OrderModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long,
    private val orderId: String,
    @ManyToOne private val customer: CustomerModel,
)
