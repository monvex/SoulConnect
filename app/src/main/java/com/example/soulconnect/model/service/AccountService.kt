package com.example.soulconnect.model.service

import com.example.soulconnect.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun createAnonymousAccount()
    suspend fun authenticate(email: String, password: String)
    suspend fun linkAccount(email: String, password: String)
}