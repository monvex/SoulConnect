package com.example.soulconnect.model


data class User(
    val id: String = "",
    val name: String = "",
    val age: String = "",
    var description: String = "",
    val city: String = "",
    val tagList: List<String> = emptyList()
)