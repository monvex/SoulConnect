package com.example.soulconnect.model.service

import com.example.soulconnect.model.User
import kotlinx.coroutines.flow.Flow

interface SearchService {
    val users: Flow<List<User>>
    suspend fun getUsers(): List<User?>
    suspend fun getSortedCandidates(user: User?, otherUsers: List<User?>): List<User?>
}