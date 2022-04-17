package io.jonathanlee.clipboardapi.service.customer.impl

import io.jonathanlee.clipboardapi.dto.customer.CustomerDto
import io.jonathanlee.clipboardapi.model.ModelConstants
import io.jonathanlee.clipboardapi.model.customer.CustomerModel
import io.jonathanlee.clipboardapi.repository.customer.CustomerRepository
import io.jonathanlee.clipboardapi.service.SecureRandomService
import io.jonathanlee.clipboardapi.service.customer.CustomerService
import org.springframework.stereotype.Service
import java.util.*

/**
 * Implementation of customer service interface used to handle customer data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val secureRandomService: SecureRandomService
) : CustomerService {

    /**
     * Implementation of create customer function used to create a customer.
     *
     * @param customerDto customer data used to create and persist customer entity
     * @return optional containing persisted customer data if present
     */
    override fun createCustomer(customerDto: CustomerDto): Optional<CustomerDto> {
        val customerModel = CustomerModel(
            ModelConstants.DEFAULT_ENTITY_ID_VALUE,
            this
                .secureRandomService
                .generateSecureRandomString(ModelConstants.DEFAULT_ENTITY_EXTERNAL_ID_LENGTH)
        )

        val customer = this.customerRepository.save(customerModel)
        return Optional.of(
            CustomerDto(
                customer.customerId
            )
        )
    }

}
