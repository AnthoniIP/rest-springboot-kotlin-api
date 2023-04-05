package com.ipsoft.restspringbootkotlinapi.person.controller

import com.ipsoft.restspringbootkotlinapi.base.extensions.generateRandomString
import com.ipsoft.restspringbootkotlinapi.person.model.Person
import com.ipsoft.restspringbootkotlinapi.person.services.PersonService
import jakarta.websocket.server.PathParam
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService


    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable id: Long?) = service.findById(id)

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() = service.findByAll()

    @PostMapping(
        value = ["/new"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createPerson(@RequestBody person: Person) = service.createPerson(person)

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePerson(@PathVariable id: Long?) = service.deletePerson(id)

}