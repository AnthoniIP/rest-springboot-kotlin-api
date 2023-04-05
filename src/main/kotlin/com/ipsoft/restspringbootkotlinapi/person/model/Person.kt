package com.ipsoft.restspringbootkotlinapi.person.model

data class Person(
    var id: Long = 0,
    var name: String,
    var gender: String,
    var address: String
) {
    companion object {
        fun getMockList(size: Int = 10): List<Person> {
            val names = listOf("Alice", "Bob", "Carol", "David", "Eva")
            val genders = listOf("Female", "Male")
            val addresses = listOf("Street A", "Street B", "Street C", "Street D", "Street E")

            return List(size) { index ->
                Person(
                    id = index.toLong(),
                    name = names.random(),
                    gender = genders.random(),
                    address = addresses.random()
                )
            }
        }

    }
}