package com.example.soulconnect.screens.search

import com.example.soulconnect.model.User

data class SearchUiState(
    val currentUser: User? = User(),
    val candidate: User? = User(),
    val candidatesList: List<User?> = emptyList(),
)