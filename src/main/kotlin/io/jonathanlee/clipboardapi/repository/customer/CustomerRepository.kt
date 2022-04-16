package io.jonathanlee.clipboardapi.repository.customer

import io.jonathanlee.clipboardapi.model.customer.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Long>
