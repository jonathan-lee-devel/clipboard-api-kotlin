package io.jonathanlee.registrationservice.service

interface RandomService {

    fun generateNewId(): String

    fun generateNewTokenValue(): String

}
