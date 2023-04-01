package com.ipsoft.restspringbootkotlinapi

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(): Greeting = Greeting(counter.incrementAndGet(), "Hello, World")
}