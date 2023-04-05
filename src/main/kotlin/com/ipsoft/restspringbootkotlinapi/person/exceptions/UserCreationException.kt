package com.ipsoft.restspringbootkotlinapi.person.exceptions

class UserCreationException(exception: String = "Error creating user") : RuntimeException(exception) {
}