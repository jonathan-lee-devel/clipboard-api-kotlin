package io.jonathanlee.clipboardapi.service.impl

import io.jonathanlee.clipboardapi.service.SecureRandomService
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class SecureRandomServiceImpl : SecureRandomService {

    companion object {
        const val SECURE_RANDOM_ALGORITHM = "NativePRNG"
    }

    private val secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)

    override fun generateSecureRandomString(length: Int): String {
        val stringBuffer = StringBuffer()
        while (stringBuffer.length < length) {
            stringBuffer.append(Integer.toHexString(this.secureRandom.nextInt()))
        }

        return stringBuffer.toString().substring(0, length)
    }

}
