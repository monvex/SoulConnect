package com.example.soulconnect.model

data class User(
    val id: String = "",
    val name: String = "",
    val age: String = "",
    val description: String = "",
    val city: String = "",
    var tagList: MutableList<String> = mutableListOf(),
    val avatar: String = "",
    var likeList: MutableList<String> = mutableListOf(),
    val chats: MutableList<String> = mutableListOf(),
    var coefficient: Double = 0.0,
)