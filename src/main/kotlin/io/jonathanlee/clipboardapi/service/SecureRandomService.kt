package io.jonathanlee.clipboardapi.service

interface SecureRandomService {

    fun generateSecureRandomString(length: Int): String

}