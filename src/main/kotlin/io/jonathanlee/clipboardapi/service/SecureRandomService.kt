package io.jonathanlee.clipboardapi.service

/**
 * Secure random service interface used to generate cryptographically secure random values.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface SecureRandomService {

    /**
     * Function to generate secure random string function used to generate secure random string.
     *
     * @param length length of the secure random string to be generated
     * @return generated secure random string of length provided
     */
    fun generateSecureRandomString(length: Int): String

}