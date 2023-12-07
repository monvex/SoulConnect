package com.example.soulconnect.screens.chats

import com.example.soulconnect.model.User


data class ChatsUiState(
    val interlocutors: MutableList<User?> = mutableListOf(),
    val currentUser: User? = User()
)