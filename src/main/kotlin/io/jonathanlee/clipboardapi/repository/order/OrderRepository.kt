package io.jonathanlee.clipboardapi.repository.order

import io.jonathanlee.clipboardapi.model.order.OrderModel
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderModel, Long>
