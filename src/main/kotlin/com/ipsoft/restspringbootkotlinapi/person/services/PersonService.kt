package com.ipsoft.restspringbootkotlinapi.person.services

import com.ipsoft.restspringbootkotlinapi.base.extensions.generateRandomString
import com.ipsoft.restspringbootkotlinapi.person.model.Person
import java.util.concurrent.atomic.AtomicLong
import org.springframework.stereotype.Service

@Service
class PersonService {
    private val counter = AtomicLong()
    fun findById(id: Long?): Person {
        return Person(
            counter.incrementAndGet(),
            "".generateRandomString(),
            "".generateRandomString(),
            "".generateRandomString()
        )
    }

}