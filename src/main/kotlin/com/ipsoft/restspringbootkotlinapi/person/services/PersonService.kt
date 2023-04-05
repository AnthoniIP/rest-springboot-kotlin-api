package com.ipsoft.restspringbootkotlinapi.person.services

import com.ipsoft.restspringbootkotlinapi.base.extensions.generateRandomString
import com.ipsoft.restspringbootkotlinapi.person.exceptions.NoPersonLocatedException
import com.ipsoft.restspringbootkotlinapi.person.exceptions.UserCreationException
import com.ipsoft.restspringbootkotlinapi.person.model.Person
import java.util.concurrent.atomic.AtomicLong
import org.springframework.stereotype.Service

@Service
class PersonService {

    private val persons: MutableList<Person> = Person.getMockList(30).toMutableList()

    fun findById(id: Long?): Person {
        id ?: throw NoPersonLocatedException("No person located with null id")

        return persons.find { it.id == id }
            ?: throw NoPersonLocatedException("No person located with id $id")
    }

    fun findByAll(): List<Person> = persons

    fun createPerson(person: Person): Person {

        try {
            val id = AtomicLong(persons.size.toLong())
            val newPerson = person.copy(id = id.incrementAndGet())
            persons.add(newPerson)
            return newPerson
        } catch (e: Exception) {
            throw UserCreationException("Error creating user: ${e.message}")
        }


    }

    fun deletePerson(id: Long?): Boolean {
        id ?: throw NoPersonLocatedException("No person located with null id")

        return if (persons.removeIf { it.id == id }) true
        else throw NoPersonLocatedException("No person located with id $id")
    }
}