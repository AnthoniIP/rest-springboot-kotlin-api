package com.ipsoft.restspringbootkotlinapi.person.model

data class Person(
    var id: Long = 0,
    var name: String,
    var gender: String,
    var address: String
)