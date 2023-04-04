package com.ipsoft.restspringbootkotlinapi.base.extensions

fun String.generateRandomString(length: Int = 10): String {
    val allowedChars = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}