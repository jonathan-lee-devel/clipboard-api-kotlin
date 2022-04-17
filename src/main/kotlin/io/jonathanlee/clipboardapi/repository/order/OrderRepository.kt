package io.jonathanlee.clipboardapi.repository.order

import io.jonathanlee.clipboardapi.model.order.OrderModel
import org.springframework.data.repository.CrudRepository

/**
 * Repository interface for order entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface OrderRepository : CrudRepository<OrderModel, Long>
