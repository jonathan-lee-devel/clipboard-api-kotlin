package io.jonathanlee.clipboardapi.service.customer

import io.jonathanlee.clipboardapi.dto.customer.CustomerDto
import java.util.*

/**
 * Customer service interface used to handle customer data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface CustomerService {

    fun createCustomer(customerDto: CustomerDto): Optional<CustomerDto>

}
