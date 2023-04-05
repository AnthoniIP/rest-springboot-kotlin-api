package com.ipsoft.restspringbootkotlinapi.person.exceptions

class NoPersonLocatedException(exception: String = "No person located") : RuntimeException(exception)