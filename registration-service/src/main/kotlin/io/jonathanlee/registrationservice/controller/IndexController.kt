package io.jonathanlee.registrationservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/")
class IndexController {

    @GetMapping
    fun getIndex(): ResponseEntity<Instant> {
        return ResponseEntity.status(HttpStatus.OK).body(Instant.now())
    }

}
