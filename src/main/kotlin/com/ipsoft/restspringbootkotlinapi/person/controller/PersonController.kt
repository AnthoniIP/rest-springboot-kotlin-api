package com.ipsoft.restspringbootkotlinapi.person.controller

import com.ipsoft.restspringbootkotlinapi.base.extensions.generateRandomString
import com.ipsoft.restspringbootkotlinapi.person.model.Person
import com.ipsoft.restspringbootkotlinapi.person.services.PersonService
import jakarta.websocket.server.PathParam
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService


    @RequestMapping(value = ["/{id}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun findById(@PathVariable id: Long?) = service.findById(id)

    @RequestMapping(produces = ["application/json"], method = [RequestMethod.GET])
    fun findAll() = service.findByAll()

}