package io.jonathanlee.clipboardapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClipboardApiApplication

fun main(args: Array<String>) {
	runApplication<ClipboardApiApplication>(*args)
}
