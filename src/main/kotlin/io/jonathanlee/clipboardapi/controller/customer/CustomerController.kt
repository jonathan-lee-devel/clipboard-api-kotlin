package io.jonathanlee.clipboardapi.controller.customer

import io.jonathanlee.clipboardapi.dto.customer.CustomerDto
import io.jonathanlee.clipboardapi.service.customer.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller for customers.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    /**
     * Basic logger.
     */
    private val log = LoggerFactory.getLogger(CustomerController::class.java)

    /**
     * @param customerDto customer data wrapped in customer DTO
     * @return response entity containing status as well as created customer DTO if present
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> {
        val createdCustomer = this.customerService.createCustomer(customerDto)
        return if (createdCustomer.isEmpty) {
            log.error("Internal server error has occurred at POST /customers")
            ResponseEntity.internalServerError().build()
        } else {
            ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer.get())
        }
    }

}
