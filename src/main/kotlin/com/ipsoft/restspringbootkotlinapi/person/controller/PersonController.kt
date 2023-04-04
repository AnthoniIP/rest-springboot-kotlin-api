package com.ipsoft.restspringbootkotlinapi.person.controller

import com.ipsoft.restspringbootkotlinapi.base.extensions.generateRandomString
import com.ipsoft.restspringbootkotlinapi.person.model.Person
import com.ipsoft.restspringbootkotlinapi.person.services.PersonService
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    private val logger = Logger.getLogger(PersonController::class.java.name)

    @Autowired
    private lateinit var service: PersonService


    @RequestMapping(value = ["/{id}"], produces = ["application/json"], method = [RequestMethod.GET])
    fun findById(id: Long?) = service.findById(id)

}