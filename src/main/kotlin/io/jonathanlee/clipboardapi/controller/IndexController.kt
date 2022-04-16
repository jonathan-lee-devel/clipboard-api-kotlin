package io.jonathanlee.clipboardapi.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
@RequestMapping("/")
class IndexController {

    private val log: Logger = LoggerFactory.getLogger(IndexController::class.java)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getIndex(): ResponseEntity<ZonedDateTime> {
        log.info("Testing logger")
        return ResponseEntity.ok(ZonedDateTime.now())
    }

}
