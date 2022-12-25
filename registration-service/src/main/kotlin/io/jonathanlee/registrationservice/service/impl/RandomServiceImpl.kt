package io.jonathanlee.registrationservice.service.impl

import io.jonathanlee.registrationservice.service.RandomService
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class RandomServiceImpl : RandomService {

    companion object {
        const val ID_LENGTH = 32
        const val TOKEN_VALUE_LENGTH = 64
    }

    override fun generateNewId(): String {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH)
    }

    override fun generateNewTokenValue(): String {
        return RandomStringUtils.randomAlphanumeric(TOKEN_VALUE_LENGTH)
    }

}
