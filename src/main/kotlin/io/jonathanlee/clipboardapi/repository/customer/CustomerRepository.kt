package io.jonathanlee.clipboardapi.repository.customer

import io.jonathanlee.clipboardapi.model.customer.CustomerModel
import org.springframework.data.repository.CrudRepository

/**
 * Repository interface for customer entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface CustomerRepository : CrudRepository<CustomerModel, Long>
