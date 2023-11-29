package com.example.soulconnect.model.service

import com.example.soulconnect.model.User

interface SearchService {
    suspend fun getUsers(): List<User>
    suspend fun getSortedCandidates(user: User, otherUsers: List<User>): List<User>

}