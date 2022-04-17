package io.jonathanlee.clipboardapi.service.impl

import io.jonathanlee.clipboardapi.service.SecureRandomService
import org.springframework.stereotype.Service
import java.security.SecureRandom

/**
 * Implementation of secure random service used to generate cryptographically secure random values.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class SecureRandomServiceImpl : SecureRandomService {

    companion object {
        const val SECURE_RANDOM_ALGORITHM = "NativePRNG"
    }

    private val secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)

    /**
     * Implementation of generate secure random string function used to generate secure random string.
     *
     * @param length length of the secure random string to be generated
     * @return generated secure random string of length provided
     */
    override fun generateSecureRandomString(length: Int): String {
        val stringBuffer = StringBuffer()
        while (stringBuffer.length < length) {
            stringBuffer.append(Integer.toHexString(this.secureRandom.nextInt()))
        }

        return stringBuffer.toString().substring(0, length)
    }

}
